package com.zzt8888.base;

public interface Presenter<T> {

    void attachView(T t);

    void detach();
}