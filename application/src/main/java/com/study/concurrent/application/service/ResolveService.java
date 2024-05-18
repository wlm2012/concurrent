package com.study.concurrent.application.service;

public interface ResolveService<T, U> {

    T resolve(U u);
}
