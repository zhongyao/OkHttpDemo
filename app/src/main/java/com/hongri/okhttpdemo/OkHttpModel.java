package com.hongri.okhttpdemo;

import com.hongri.okhttpdemo.okhttp.CommonOkHttpClient;
import com.hongri.okhttpdemo.okhttp.listener.DisposeDataHandler;
import com.hongri.okhttpdemo.okhttp.listener.IDisposeDataCallback;
import com.hongri.okhttpdemo.okhttp.listener.IDisposeDataListener;
import com.hongri.okhttpdemo.okhttp.request.CommonRequest;
import com.hongri.okhttpdemo.okhttp.request.RequestParams;
import com.hongri.okhttpdemo.util.OkHttpRequestType.RequestType;

/**
 * Created by zhongyao on 2019-07-20.
 */
public class OkHttpModel extends OkHttpBaseModel {

    private IDisposeDataCallback mListener;

    public void setListener(IDisposeDataCallback listener) {
        mListener = listener;
    }

    public void getRequest(String url, RequestParams params) {

        CommonOkHttpClient.get(CommonRequest.createGetRequest(url, params), new DisposeDataHandler(
            new IDisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {
                    if (mListener != null) {
                        mListener.onSuccess(responseObj, RequestType.GET);
                    }
                }

                @Override
                public void onFailure(Object errorObj) {
                    if (mListener != null) {}
                    mListener.onFailure(errorObj, RequestType.GET);
                }
            }));
    }

    public void postRequest(String url, RequestParams params) {
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url, params), new DisposeDataHandler(
            new IDisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {
                    if (mListener != null) {
                        mListener.onSuccess(responseObj,RequestType.POST);
                    }
                }

                @Override
                public void onFailure(Object errorObj) {
                    if (mListener != null) {}
                    mListener.onFailure(errorObj,RequestType.POST);
                }
            }));
    }
}
