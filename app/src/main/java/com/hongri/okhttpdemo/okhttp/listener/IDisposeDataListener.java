package com.hongri.okhttpdemo.okhttp.listener;

/**
 * Created by zhongyao on 2019-07-20.
 */
public interface IDisposeDataListener {

    /**
     * 获取成功
     *
     * @param responseObj
     */
    void onSuccess(Object responseObj);

    /**
     * 获取失败
     *
     * @param errorObj
     */
    void onFailure(Object errorObj);
}
