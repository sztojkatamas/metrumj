package com.example.demo;

import com.metrumj.benchmark.BenchmarkTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(BenchmarkTestExtension.class)
class DemoServiceTest {

    @Autowired
    DemoService demoService;

    @Test
    void DemoServiceTests() throws InterruptedException {
        demoService.doHeavyWork();
        demoService.doHeavyWork2();
    }
}