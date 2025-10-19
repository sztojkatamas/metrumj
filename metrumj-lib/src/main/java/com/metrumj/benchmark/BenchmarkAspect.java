package com.metrumj.benchmark;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Aspect
public class BenchmarkAspect {

    private final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
    private final com.sun.management.OperatingSystemMXBean osBean =
            (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    @Around("execution(@com.metrumj.benchmark.Benchmark * *(..))")
    public Object benchmark(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!BenchmarkConfig.isBenchmarkEnabled()) {
            return joinPoint.proceed();
        }

        long usedBefore = usedMemoryKb();
        long start = System.nanoTime();

        // Metric trackers
        final int[] peakThreads = {threadMXBean.getThreadCount()};
        final double[] peakCpu = {getProcessCpuLoad()};

        // Background sampler
        AtomicBoolean running = new AtomicBoolean(true);
        Thread sampler = new Thread(() -> {
            while (running.get()) {
                try {
                    int threads = threadMXBean.getThreadCount();
                    double cpu = getProcessCpuLoad();

                    if (threads > peakThreads[0]) peakThreads[0] = threads;
                    if (cpu > peakCpu[0]) peakCpu[0] = cpu;

                    Thread.sleep(50); // Sampling time
                } catch (InterruptedException ignored) {
                    break;
                }
            }
        });
        sampler.setDaemon(true);
        sampler.start();

        Object result;
        try {
            result = joinPoint.proceed();
        } finally {
            running.set(false);
            sampler.join();
        }

        long durationMs = (System.nanoTime() - start) / 1_000_000;
        long usedAfter = usedMemoryKb();
        long memoryUsed = Math.max(usedAfter - usedBefore, 0);

        BenchmarkRegistry.add(new BenchmarkResult(
                Optional.ofNullable(BenchmarkContext.getTestName()).orElse("Application"),
                joinPoint.getSignature().toShortString(),
                durationMs,
                memoryUsed,
                peakThreads[0],
                peakCpu[0]
        ));

        return result;
    }

    private long usedMemoryKb() {
        Runtime runtime = Runtime.getRuntime();
        return (runtime.totalMemory() - runtime.freeMemory()) / 1024;
    }

    private double getProcessCpuLoad() {
        double load = osBean.getProcessCpuLoad();
        return (load >= 0) ? load * 100.0 : 0.0;
    }
}
