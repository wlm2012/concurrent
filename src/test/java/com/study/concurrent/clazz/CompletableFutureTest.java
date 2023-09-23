package com.study.concurrent.clazz;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CompletableFutureTest {


    @Test
    void test() {

        long start = System.currentTimeMillis();

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            sleep(1);
            return 1;
        }).thenApply(e -> {
            System.out.println(e);
            sleep(2);
//            throw new RuntimeException("1");
            return e + 1;
        }).handle((e, t) -> {
                    if (t != null) {
                        return 0;
                    }
                    return e + 1;
                }
        ).exceptionally(e -> {
            log.info("exceptionally");
            return 0;
        });

        log.info(String.valueOf(completableFuture.join()));

        log.info("结束：{}", System.currentTimeMillis() - start);

    }


    private static void sleep(int time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
