package com.study.concurrent.application.service;

public interface SaveService<T, U> {

    void save(T t, U u);
}
