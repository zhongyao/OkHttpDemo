package com.hongri.okhttpdemo.okhttp.listener;

/**
 * Created by zhongyao on 2019-07-20.
 */
public class DisposeDataHandler {

    public IDisposeDataListener mListener;

    public Class<?> mClass;

    public String mSource = null;

    public DisposeDataHandler(IDisposeDataListener listener) {
        mListener = listener;
    }

    public DisposeDataHandler(IDisposeDataListener listener, Class<?> clazz) {
        mListener = listener;
        mClass = clazz;
    }

    public DisposeDataHandler(IDisposeDataListener listener, String source) {
        mListener = listener;
        mSource = source;
    }
}
