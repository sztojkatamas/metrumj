package com.metrumj.benchmark;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class BenchmarkReportWriter {

    public void writeHtmlReport(String filePath, List<BenchmarkResult> results) {
        Map<String, List<BenchmarkResult>> grouped =
                results.stream().collect(Collectors.groupingBy(BenchmarkResult::getTestName));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("<html><head><title>Benchmark Report</title>");
            writer.write("<style>");
            writer.write("body { font-family: Arial; padding: 20px; }");
            writer.write("h2 { margin-top: 40px; }");
            writer.write("table { border-collapse: collapse; width: 100%; margin-bottom: 40px; }");
            writer.write("th, td { border: 1px solid #ccc; padding: 8px; text-align: center; }");
            writer.write("th { background-color: #f2f2f2; }");
            writer.write("</style>");
            writer.write("</head><body>");
            writer.write("<h1>Benchmark Report</h1>");

            for (Map.Entry<String, List<BenchmarkResult>> entry : grouped.entrySet()) {
                writer.write("<h2>" + entry.getKey() + "</h2>");
                writer.write("<table>");
                writer.write("<tr>");
                writer.write("<th>Method</th>");
                writer.write("<th>Wall Time (ms)</th>");
                writer.write("<th>CPU Time (ms)</th>");
                writer.write("<th>CPU Usage (%)</th>");
                writer.write("<th>Memory Used (KB)</th>");
                writer.write("<th>Peak Threads</th>");
                writer.write("<th>Peak CPU Load (%)</th>");
                writer.write("</tr>");

                for (BenchmarkResult result : entry.getValue()) {
                    double cpuUsage = (result.getCpuTimeMs() >= 0 && result.getDurationMs() > 0)
                            ? ((double) result.getCpuTimeMs() * 100 / result.getDurationMs())
                            : -1;

                    writer.write("<tr>");
                    writer.write("<td>" + result.getMethodName() + "</td>");
                    writer.write("<td>" + result.getDurationMs() + "</td>");
                    writer.write("<td>" + (result.getCpuTimeMs() >= 0 ? result.getCpuTimeMs() : "N/A") + "</td>");
                    writer.write("<td>" + (cpuUsage >= 0 ? String.format("%.1f", cpuUsage) : "N/A") + "</td>");
                    writer.write("<td>" + result.getMemoryKb() + "</td>");
                    writer.write("<td>" + result.getPeakThreadCount() + "</td>");
                    writer.write("<td>" + String.format("%.2f", result.getPeakCpuLoad()) + "</td>");
                    writer.write("</tr>");
                }

                writer.write("</table>");
            }

            writer.write("</body></html>");

        } catch (IOException e) {
            System.err.println("❌ Failed to write benchmark report: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
