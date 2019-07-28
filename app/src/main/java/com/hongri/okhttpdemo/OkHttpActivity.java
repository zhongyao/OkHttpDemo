package com.hongri.okhttpdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.hongri.okhttpdemo.util.OkConstant;

/**
 * 用来测试OKHttp的简单使用
 */
public class OkHttpActivity extends Activity implements View.OnClickListener {

    private Button getBtn, postBtn, uploadBtn, downloadBtn;
    private TextView getShow;
    private ImageView downloadShow;
    private TextView imageLoading;

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

        getBtn.setOnClickListener(this);
        postBtn.setOnClickListener(this);
        uploadBtn.setOnClickListener(this);
        downloadBtn.setOnClickListener(this);

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
                mPresenter.postRequest(OkConstant.POST_URL, null);
                break;

            case R.id.uploadBtn:

                break;
            case R.id.downloadBtn:
                mPresenter.downloadImageRequest(OkConstant.DOWNLOAD_URL, null);
                break;

            default:
                break;
        }
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
                downloadShow.setImageBitmap((Bitmap)resultObj);
            }
        }
    }

    public void showDownloadImageLoading(int progress) {
        imageLoading.setText(progress);
    }
}
