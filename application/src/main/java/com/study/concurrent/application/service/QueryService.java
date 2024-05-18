package com.study.concurrent.application.service;

public interface QueryService<T, U> {

    T query(U u);
}
