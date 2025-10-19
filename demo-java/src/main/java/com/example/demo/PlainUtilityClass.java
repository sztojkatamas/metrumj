package com.example.demo;

import com.metrumj.benchmark.Benchmark;
import java.util.Random;

public class PlainUtilityClass {
    static Random random = new Random();

    private PlainUtilityClass() {
        throw new IllegalStateException("Utility class");
    }

    @Benchmark
    static String generateString() {
        return random.ints(97, 123)
                .limit(1024)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Benchmark
    static String generate5000String() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5000; i++) {
            String result = random.ints(97, 123)
                    .limit(1024)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            sb.append(result);
        }
        return sb.toString();
    }
}
