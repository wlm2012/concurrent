package com.study.concurrent.structuredTaskScope;

import org.junit.jupiter.api.Test;

import java.util.concurrent.StructuredTaskScope;

public class StructuredTaskScopeTest {

    @Test
    void test() throws InterruptedException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            StructuredTaskScope.Subtask<Integer> fork = scope.fork(() -> 1);
            scope.join();
            System.out.println(fork.get());
        }
    }
}
