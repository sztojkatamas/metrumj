package com.metrumj.benchmark;

/**
 * Thread-local context holder for the currently executing test name.
 * Used to group benchmark results under a test when applicable.
 */
public class BenchmarkContext {

    private static final ThreadLocal<String> testName = new ThreadLocal<>();

    public static void setTestName(String name) {
        testName.set(name);
    }

    public static String getTestName() {
        return testName.get();
    }

    public static void clear() {
        testName.remove();
    }

    // Prevent instantiation
    private BenchmarkContext() {
        throw new IllegalStateException("Utility class");
    }
}