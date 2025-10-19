package com.metrumj.benchmark;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class BenchmarkReportWriter {

    public void writeHtmlReport(String filePath, List<BenchmarkResult> results) {
        Map<String, List<BenchmarkResult>> grouped = results.stream()
                .collect(Collectors.groupingBy(r -> r.testName));

        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            writer.println("<html><head><title>Benchmark Report</title>");
            writer.println("<style>body{font-family:sans-serif;} table{border-collapse:collapse;} td,th{padding:4px 8px;border:1px solid #ccc;}</style>");
            writer.println("</head><body><h1>Benchmark Report</h1>");

            for (var entry : grouped.entrySet()) {
                writer.printf("<h2>%s</h2>\n", entry.getKey());
                writer.println("<table><tr><th>Method</th><th>Time (ms)</th><th>Memory (KB)</th><th>Peak Threads</th><th>Peak CPU (%)</th></tr>");

                for (BenchmarkResult r : entry.getValue()) {
                    writer.printf(
                            "<tr><td>%s</td><td>%d</td><td>%d</td><td>%d</td><td>%.1f</td></tr>\n",
                            r.methodName, r.durationMs, r.memoryKb, r.peakThreadCount, r.peakCpuLoad
                    );
                }
                writer.println("</table><br/>");
            }

            writer.println("</body></html>");
        } catch (IOException e) {
            System.err.println("❌ Failed to write benchmark report: " + e.getMessage());
        }
    }
}
