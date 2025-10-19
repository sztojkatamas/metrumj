package com.metrumj.benchmark;

public class BenchmarkResult {
    public final String testName;
    public final String methodName;
    public final long durationMs;
    public final long memoryKb;
    public final int peakThreadCount;
    public final double peakCpuLoad;

    public BenchmarkResult(String testName, String methodName, long durationMs, long memoryKb,
                           int peakThreadCount, double peakCpuLoad) {
        this.testName = testName;
        this.methodName = methodName;
        this.durationMs = durationMs;
        this.memoryKb = memoryKb;
        this.peakThreadCount = peakThreadCount;
        this.peakCpuLoad = peakCpuLoad;
    }

    @Override
    public String toString() {
        return "BenchmarkResult{" +
                "testName='" + testName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", durationMs=" + durationMs +
                ", memoryKb=" + memoryKb +
                ", peakThreadCount=" + peakThreadCount +
                ", peakCpuLoad=" + String.format("%.2f", peakCpuLoad) +
                "%}";
    }
}
