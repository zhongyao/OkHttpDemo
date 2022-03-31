package com.hongri.okhttpdemo.rxjava;

/**
 * @author：hongri
 * @date：3/30/22
 * @description：
 */
public interface IBaseListener<T> {

    void success(T t);

    void failure(T t);
}
