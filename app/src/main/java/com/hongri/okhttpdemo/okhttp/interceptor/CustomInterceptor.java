package com.hongri.okhttpdemo.okhttp.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 自定义【应用拦截器 -- 最先执行的拦截器 -- 通常只会执行一次 -- 通常用于统计客户端网络请求发起情况】
 */
public class CustomInterceptor implements Interceptor {

    private static final String TAG = "CustomInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        Log.d(TAG, "request:" + request + " response:" + response.toString());
        return response;
    }
}
