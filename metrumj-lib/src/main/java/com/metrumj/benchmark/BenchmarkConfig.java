package com.metrumj.benchmark;

public class BenchmarkConfig {

    private static final boolean enabled =
            Boolean.parseBoolean(System.getProperty("benchmark.enabled", "false"));

    public static boolean isBenchmarkEnabled() {
        return enabled;
    }

    private BenchmarkConfig() {}
}
