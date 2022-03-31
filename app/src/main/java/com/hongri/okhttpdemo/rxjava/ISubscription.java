package com.hongri.okhttpdemo.rxjava;

import rx.Subscription;

/**
 * @author：hongri
 * @date：3/30/22
 * @description：
 */
public interface ISubscription<T> {

    void add(T tag, Subscription subscription);

    void remove(T tag);

    void removeAll();

    void cancel(T tag);

    void cancelAll();

    String getName(T tag);
}
