package com.hongri.okhttpdemo.okhttp.listener;

import com.hongri.okhttpdemo.util.OkHttpRequestType.RequestType;

/**
 * Created by zhongyao on 2019-07-20.
 */
public interface IDisposeDataCallback extends IDisposeBaseListener {

    /**
     * 获取成功
     *
     * @param responseObj
     */
    void onSuccess(Object responseObj, Enum requestType);

    /**
     * 获取失败
     *
     * @param errorObj
     */
    void onFailure(Object errorObj, Enum requestType);

    /**
     * 下载进度
     * @param progress
     * @param type
     */
    void onProgress(int progress, RequestType type);
}
