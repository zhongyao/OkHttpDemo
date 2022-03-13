package com.hongri.okhttpdemo.okhttp;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.hongri.okhttpdemo.okhttp.dns.OkHttpDNS;
import com.hongri.okhttpdemo.okhttp.eventlistener.HttpEventListener;
import com.hongri.okhttpdemo.okhttp.https.SSLSocketClient;
import com.hongri.okhttpdemo.okhttp.interceptor.CustomInterceptor;
import com.hongri.okhttpdemo.okhttp.interceptor.NetworkInterceptor;
import com.hongri.okhttpdemo.okhttp.listener.DisposeDataHandler;
import com.hongri.okhttpdemo.okhttp.response.CommonFileCallback;
import com.hongri.okhttpdemo.okhttp.response.CommonImageCallback;
import com.hongri.okhttpdemo.okhttp.response.CommonJsonCallback;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhongyao on 2019-07-20.
 * <p>
 * 发送请求工具类
 */
public class CommonOkHttpClient {

    private static final String TAG = "CommonOkHttpClient";
    private static final int TIME_OUT = 20;
    private static OkHttpClient mOkHttpClient;

    static {
        OkHttpClient.Builder okHttpBuilder = new Builder();
        /**
         * addInterceptor 应用拦截器：
         * 应用拦截器因为只会调用一次，通常用于统计客户端的网络请求发起情况。
         */
        okHttpBuilder.addInterceptor(new CustomInterceptor());

        /**
         * addNetworkInterceptor 网络拦截器：
         * 网络拦截器一次调用代表了一定会发起一次网络通信，因此通常可用于网络链路上传输的数据。
         */
        okHttpBuilder.addNetworkInterceptor(new NetworkInterceptor());

        okHttpBuilder.dns(OkHttpDNS.getInstance());
        okHttpBuilder.eventListenerFactory(HttpEventListener.FACTORY);
        okHttpBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.followRedirects(true);
        okHttpBuilder.sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), SSLSocketClient.trustManager);
        okHttpBuilder.hostnameVerifier(SSLSocketClient.getHostnameVerifier());

        mOkHttpClient = okHttpBuilder.build();
    }

    public static Call get(Request request, DisposeDataHandler handler) {

        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handler));
        return call;
    }

    public static Call post(Request request, DisposeDataHandler handler) {

        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handler));
        return call;
    }

    public static Call downloadFile(Request request, DisposeDataHandler handler) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonFileCallback(handler));
        return call;
    }

    public static Call downloadImage(Request request, DisposeDataHandler handler) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonImageCallback(handler));
        return call;
    }

}
