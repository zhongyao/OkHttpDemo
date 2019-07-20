package com.hongri.okhttpdemo.okhttp;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import com.hongri.okhttpdemo.okhttp.listener.DisposeDataHandler;
import com.hongri.okhttpdemo.okhttp.response.CommonFileCallback;
import com.hongri.okhttpdemo.okhttp.response.CommonJsonCallback;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;

/**
 * Created by zhongyao on 2019-07-20.
 *
 * 发送请求工具类
 */
public class CommonOkHttpClient {

    private static final int TIME_OUT = 20;
    private static OkHttpClient mOkHttpClient;

    static {
        OkHttpClient.Builder okHttpBuilder = new Builder();
        okHttpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        okHttpBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);

        okHttpBuilder.followRedirects(true);

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

    public static Call downloadFile(Request request, DisposeDataHandler handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonFileCallback(handle));
        return call;
    }

}
