package com.study.concurrent.application.service;

import java.util.concurrent.CompletableFuture;

public interface SaveService<T, U> {

    CompletableFuture<Void> save(T t, U u);
}
