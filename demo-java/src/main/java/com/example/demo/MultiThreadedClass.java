package com.example.demo;

import com.metrumj.benchmark.Benchmark;

import java.util.*;
import java.util.concurrent.*;

public class MultiThreadedClass {

    private int availableCores;

    public MultiThreadedClass() {
        availableCores = Runtime.getRuntime().availableProcessors();
    }

    @Benchmark
    public long findPrimes(int requestedThreads, int upperLimit) {
        int threads = Math.min(requestedThreads, availableCores);
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Future<Long>> futures = new ArrayList<>();

        int chunkSize = upperLimit / threads;
        for (int i = 0; i < threads; i++) {
            final int start = i * chunkSize + 1;
            final int end = (i == threads - 1) ? upperLimit : (i + 1) * chunkSize;

            futures.add(executor.submit(() -> countPrimesInRange(start, end)));
        }

        long totalPrimes = futures.stream().mapToLong(f -> {
            try {
                return f.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).sum();

        executor.shutdown();
        return totalPrimes;
    }

    private long countPrimesInRange(int start, int end) {
        long count = 0;
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) count++;
        }
        return count;
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        if (n % 2 == 0) return n == 2;
        int sqrt = (int) Math.sqrt(n);
        for (int i = 3; i <= sqrt; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
}

