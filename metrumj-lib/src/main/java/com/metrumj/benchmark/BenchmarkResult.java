package com.metrumj.benchmark;

public class BenchmarkResult {
    private final String testName;
    private final String methodName;
    private final long durationMs;
    private final long memoryKb;
    private final int peakThreadCount;
    private final double peakCpuLoad;
    private final long cpuTimeMs;

    public BenchmarkResult(String testName, String methodName, long durationMs, long memoryKb,
                           int peakThreadCount, double peakCpuLoad, long cpuTimeMs) {
        this.testName = testName;
        this.methodName = methodName;
        this.durationMs = durationMs;
        this.memoryKb = memoryKb;
        this.peakThreadCount = peakThreadCount;
        this.peakCpuLoad = peakCpuLoad;
        this.cpuTimeMs = cpuTimeMs;
    }

    public String getTestName() {
        return testName;
    }

    public String getMethodName() {
        return methodName;
    }

    public long getDurationMs() {
        return durationMs;
    }

    public long getMemoryKb() {
        return memoryKb;
    }

    public int getPeakThreadCount() {
        return peakThreadCount;
    }

    public double getPeakCpuLoad() {
        return peakCpuLoad;
    }

    public long getCpuTimeMs() {
        return cpuTimeMs;
    }

    @Override
    public String toString() {
        return "BenchmarkResult{" +
                "testName='" + testName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", durationMs=" + durationMs +
                ", memoryKb=" + memoryKb +
                ", peakThreadCount=" + peakThreadCount +
                ", peakCpuLoad=" + String.format("%.2f", peakCpuLoad) + "%" +
                ", cpuTimeMs=" + cpuTimeMs +
                '}';
    }
}
