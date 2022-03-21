package com.hongri.okhttpdemo.retrofit;

import com.hongri.okhttpdemo.okhttp.interceptor.NetworkInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @author：hongri
 * @date：3/21/22
 * @description：
 */
public class RetrofitManager {

    private Retrofit retrofit;
    private OkHttpClient client;

    private RetrofitManager() {
        init();
    }

    private void init() {
        client = new OkHttpClient.Builder().addNetworkInterceptor(new NetworkInterceptor()).build();

    }

    private static class Holder {
        private static RetrofitManager instance = new RetrofitManager();
    }

    public static RetrofitManager getInstance() {
        return Holder.instance;
    }


    public Retrofit getRetrofitApi() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(RetrofitApi.BASE_URL)
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
