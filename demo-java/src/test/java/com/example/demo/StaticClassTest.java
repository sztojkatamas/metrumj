package com.example.demo;

import com.metrumj.benchmark.BenchmarkTestExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("benchmark")
@ExtendWith(BenchmarkTestExtension.class)
class StaticClassTest {

    @Test
    void TestingUtilityClass() {
        String result = PlainUtilityClass.generateString();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1024, result.length());
    }

    @Test
    void TestingUtilityClassAgain() {
        String result = PlainUtilityClass.generate5000String();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1024 * 5000L, result.length());
    }

}
