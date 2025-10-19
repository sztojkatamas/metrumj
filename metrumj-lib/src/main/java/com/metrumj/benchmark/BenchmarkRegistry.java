package com.metrumj.benchmark;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BenchmarkRegistry {

    private static final List<BenchmarkResult> results = new CopyOnWriteArrayList<>();

    private BenchmarkRegistry() {
        throw new IllegalStateException("Utility class");
    }

    public static void add(BenchmarkResult result) {
        results.add(result);
    }

    public static List<BenchmarkResult> getResults() {
        return Collections.unmodifiableList(results);
    }

    public static void clear() {
        results.clear();
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() ->
            new BenchmarkReportWriter().writeHtmlReport("build/benchmark-report.html", getResults())
        ));
    }
}
