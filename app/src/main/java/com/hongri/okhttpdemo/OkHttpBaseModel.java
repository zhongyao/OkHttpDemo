package com.hongri.okhttpdemo;

import com.hongri.okhttpdemo.okhttp.request.RequestParams;

/**
 * Created by zhongyao on 2019-07-20.
 */
public abstract class OkHttpBaseModel {

    public void getRequest(String url, RequestParams params) {}

    public void postRequest(String url, RequestParams params) {}
}
