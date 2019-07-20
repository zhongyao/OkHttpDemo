package com.hongri.okhttpdemo.okhttp.listener;

/**
 * Created by zhongyao on 2019-07-20.
 */
public interface IDisposeDownloadListener extends IDisposeDataListener {
    /**
     * 下载进度
     * @param progrss
     */
    void onProgress(int progrss);
}
