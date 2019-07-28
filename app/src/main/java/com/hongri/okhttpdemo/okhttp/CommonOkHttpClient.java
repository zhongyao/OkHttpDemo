package com.hongri.okhttpdemo.okhttp;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.hongri.okhttpdemo.okhttp.https.SSLSocketClient;
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
 *
 * 发送请求工具类
 */
public class CommonOkHttpClient {

    private static final int TIME_OUT = 20;
    private static OkHttpClient mOkHttpClient;

    static {
        OkHttpClient.Builder okHttpBuilder = new Builder();
        okHttpBuilder.addInterceptor(new Interceptor() {
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                return response;
            }
        });
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
