package com.metrumj.benchmark;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

/**
 * JUnit 5 extension that sets up the benchmark context
 * before each test and clears it afterward.
 */
public class BenchmarkTestExtension implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        String methodName = context.getTestMethod()
                .map(Method::getName)
                .orElse("N/A");

        BenchmarkContext.setTestName(methodName);
        System.out.println("⏱ Starting test: " + methodName);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        String methodName = context.getTestMethod()
                .map(Method::getName)
                .orElse("N/A");

        System.out.println("✅ Finished test: " + methodName);
        BenchmarkContext.clear();
    }
}
