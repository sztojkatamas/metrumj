package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MultiThreadTest {

    @Test
    void findPrimesTest() throws InterruptedException {
        MultiThreadedClass multi = new MultiThreadedClass();
        Assertions.assertEquals(25, multi.findPrimes(1, 100));
        Assertions.assertEquals(100, multi.findPrimes(6, 542));
        Assertions.assertEquals(99999, multi.findPrimes(12, 1299690));
    }
}
