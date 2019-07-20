package com.hongri.okhttpdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.hongri.okhttpdemo.util.OkConstant;

/**
 * 用来测试OKHttp的简单使用
 */
public class OkHttpActivity extends Activity implements View.OnClickListener {
    public static String YAO = "yao";

    private String url = "http://g.hiphotos.baidu.com/image/pic/item/adaf2edda3cc7cd96bf48f6f3c01213fb90e91af.jpg";

    private Button getBtn, postBtn, uploadBtn, downloadBtn;
    private TextView getShow;

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
                mPresenter.postRequest(OkConstant.POST_URL,null);
                break;

            case R.id.uploadBtn:

                break;
            case R.id.downloadBtn:

                break;

            default:
                break;
        }
    }

    public void showGetResult(Object resultObj) {
        //get请求结果展示
        getShow.setText(resultObj.toString());
    }

    public void showPostResult(Object resultObj) {
        //post请求结果展示
    }

    public void showUploadResult(Object resultObj) {
        //Upload请求结果展示
    }

    public void showDownloadResult(Object resultObj) {
        //Download请求结果展示
    }

}
