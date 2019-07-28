package com.hongri.okhttpdemo;

import com.hongri.okhttpdemo.okhttp.listener.IDisposeDataCallback;
import com.hongri.okhttpdemo.okhttp.request.RequestParams;
import com.hongri.okhttpdemo.util.OkHttpRequestType.RequestType;

/**
 * Created by zhongyao on 2019-07-20.
 */
public class OkHttpPresenter implements IDisposeDataCallback {

    private OkHttpActivity mActivity;

    public void bindView(OkHttpActivity activity) {
        mActivity = activity;
    }

    public void getRequest(String url, RequestParams params) {
        OkHttpModelManager.newInstance(OkHttpModel.class.getName()).getRequest(url, params, this);
    }

    public void postRequest(String url, RequestParams params) {
        OkHttpModelManager.newInstance(OkHttpModel.class.getName()).postRequest(url, params, this);
    }

    public void downloadImageRequest(String url, RequestParams params) {
        OkHttpModelManager.newInstance(OkHttpModel.class.getName()).downloadImageRequest(url, params, this);
    }

    @Override
    public void onSuccess(Object responseObj, Enum type) {
        if (mActivity != null) {
            if (type.equals(RequestType.GET)) {
                mActivity.showGetResult(responseObj, true);
            } else if (type.equals(RequestType.POST)) {
                mActivity.showPostResult(responseObj, true);
            } else if (type.equals(RequestType.UPLOAD)) {

            } else if (type.equals(RequestType.DOWNLOAD_IMAGE)) {
                mActivity.showDownloadImageResult(responseObj, true);
            }
        }
    }

    @Override
    public void onFailure(Object errorObj, Enum type) {
        if (mActivity != null) {
            if (type.equals(RequestType.GET)) {
                mActivity.showGetResult(errorObj, false);
            } else if (type.equals(RequestType.POST)) {
                mActivity.showPostResult(errorObj, false);
            } else if (type.equals(RequestType.UPLOAD)) {

            } else if (type.equals(RequestType.DOWNLOAD_IMAGE)) {
                mActivity.showDownloadImageResult(errorObj, false);
            }
        }
    }

    @Override
    public void onProgress(int progress, RequestType type) {
        if (mActivity != null) {
            if (type.equals(RequestType.DOWNLOAD_IMAGE)) {
                mActivity.showDownloadImageLoading(progress);
            }
        }
    }
}
