package com.example.demo;

import com.metrumj.benchmark.Benchmark;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    @Benchmark
    public void doHeavyWork() throws InterruptedException {
        Thread.sleep(300);  // simulate delay
        int[] data = new int[5_000_000];
        for (int i = 0; i < data.length; i++) {
            data[i] = i * 2;
        }
    }

    @Benchmark
    public void doHeavyWork2() throws InterruptedException {
        Thread.sleep(300);  // simulate delay
        int[] data = new int[7_000_000];
        for (int i = 0; i < data.length; i++) {
            data[i] = i * 2;
        }
    }
}
