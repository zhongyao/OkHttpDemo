package com.hongri.okhttpdemo.rxjava;
import rx.Subscriber;

/**
 * @author：hongri
 * @date：3/30/22
 * @description：
 */
public class BaseSubscriber<T> extends Subscriber<T> {

    public BaseSubscriber(IBaseListener tag) {
        SubscriptionManager.getInstance().add(tag, this);
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable t) {

    }
}
