package com.hongri.okhttpdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongri.okhttpdemo.okhttp.request.RequestParams;
import com.hongri.okhttpdemo.retrofit.ResponseBody;
import com.hongri.okhttpdemo.retrofit.RetrofitApi;
import com.hongri.okhttpdemo.retrofit.RetrofitManager;
import com.hongri.okhttpdemo.rxjava.BaseSubscriber;
import com.hongri.okhttpdemo.rxjava.IBaseListener;
import com.hongri.okhttpdemo.rxjava.SubscriptionManager;
import com.hongri.okhttpdemo.util.OkConstant;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 用来测试OKHttp的简单使用
 */
public class OkHttpActivity extends Activity implements View.OnClickListener, IBaseListener {

    private Button getBtn, postBtn, uploadBtn, downloadBtn;
    private TextView getShow;
    private ImageView downloadShow;
    private TextView imageLoading;
    private Button OkInterceptor;
    private Button useRetrofit;
    private Button retrofitRxJava;

    private OkHttpPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        initPresenter();

        getBtn = findViewById(R.id.getBtn);
        postBtn = findViewById(R.id.postBtn);
        uploadBtn = findViewById(R.id.uploadBtn);
        downloadBtn = findViewById(R.id.downloadBtn);

        getShow = findViewById(R.id.getShow);
        downloadShow = findViewById(R.id.downloadShow);
        imageLoading = findViewById(R.id.imageLoading);

        OkInterceptor = findViewById(R.id.ok_intercept);
        useRetrofit = findViewById(R.id.useRetrofit);
        retrofitRxJava = findViewById(R.id.retrofitRxJava);

        getBtn.setOnClickListener(this);
        postBtn.setOnClickListener(this);
        uploadBtn.setOnClickListener(this);
        downloadBtn.setOnClickListener(this);
        OkInterceptor.setOnClickListener(this);
        useRetrofit.setOnClickListener(this);
        retrofitRxJava.setOnClickListener(this);

    }

    private void initPresenter() {
        if (mPresenter == null) {
            mPresenter = new OkHttpPresenter();
        }

        mPresenter.bindView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getBtn:
                mPresenter.getRequest(OkConstant.GET_URL, null);
                break;
            case R.id.postBtn:
                mPresenter.postRequest(OkConstant.POST_URL, createPostParams());
                break;

            case R.id.uploadBtn:

                break;
            case R.id.downloadBtn:
                mPresenter.downloadImageRequest(OkConstant.DOWNLOAD_URL, null);
                break;
            case R.id.ok_intercept:
                break;
            case R.id.useRetrofit:
                RetrofitManager.getInstance().getRetrofitApi().
                        create(RetrofitApi.class).getBook("232", "450");
                break;
            case R.id.retrofitRxJava:
                RetrofitManager.getInstance().getRetrofitApi().
                        create(RetrofitApi.class).getHistoryBook("666", "history")
                .subscribeOn(Schedulers.io())//切换到IO线程
                .observeOn(AndroidSchedulers.mainThread())//切换到主线程
                .subscribe(new BaseSubscriber<ResponseBody>(this) {

                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        super.onNext(responseBody);
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                    }

                });
                break;
            default:
                break;
        }
    }

    private RequestParams createPostParams() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("weaid", "1");
        requestParams.put("date", "2018-08-13");
        requestParams.put("appkey", "10003");
        requestParams.put("sign", "b59bc3ef6191eb9f747dd4e83c99f2a4");
        requestParams.put("format", "json");
        return requestParams;
    }

    public void showGetResult(Object resultObj, boolean success) {
        //get请求结果展示
        getShow.setText(resultObj.toString());
    }

    public void showPostResult(Object resultObj, boolean success) {
        //post请求结果展示
    }

    public void showUploadResult(Object resultObj, boolean success) {
        //Upload请求结果展示
    }

    public void showDownloadImageResult(Object resultObj, boolean success) {
        //Download图片请求结果展示
        if (success) {
            if (resultObj instanceof Bitmap) {
                downloadShow.setImageBitmap((Bitmap) resultObj);
            }
        }
    }

    public void showDownloadImageLoading(int progress) {
        imageLoading.setText(progress);
    }

    @Override
    public void success(Object o) {

    }

    @Override
    public void failure(Object o) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SubscriptionManager.getInstance().cancel(this);
    }
}
