package com.hongri.okhttpdemo.okhttp.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 网络拦截器【一次调用代表了一定会发起一次网络通信，因此通常可用于统计网络链路上传输的数据】
 */
public class NetworkInterceptor implements Interceptor {

    private static final String TAG = "NetworkInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        Log.d(TAG, "network ---> request:" + request + " response:" + response.toString());
        return response;
    }
}
