package com.study.concurrent.utils;

public class ThreadUtils {

    private ThreadUtils() {
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
