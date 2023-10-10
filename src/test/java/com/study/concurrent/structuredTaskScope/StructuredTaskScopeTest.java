package com.study.concurrent.structuredTaskScope;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.TimeUnit;

@Slf4j
public class StructuredTaskScopeTest {

    @Test
    void test() throws InterruptedException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            long start = System.currentTimeMillis();
            StructuredTaskScope.Subtask<Integer> fork1 = scope.fork(this::fork1);
            StructuredTaskScope.Subtask<String> fork2 = scope.fork(this::fork2);
            StructuredTaskScope.Subtask<Integer> fork3 = scope.fork(this::fork3);
            scope.join();
            log.info("time:{}", System.currentTimeMillis() - start);
            log.info(String.valueOf(fork1.get()));
            log.info(fork2.get());
        } catch (Exception e) {
            log.error("Exception:", e);
        }
    }

    private Integer fork1() {
        log.info("fork1 start");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("fork1 finish");
        return 1;
    }

    private String fork2() {
        log.info("fork2 start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("fork2 finish");
        return "2";
    }

    private Integer fork3() {
        throw new RuntimeException("3");
    }


}
