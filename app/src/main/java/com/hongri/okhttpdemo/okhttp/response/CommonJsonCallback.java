package com.hongri.okhttpdemo.okhttp.response;

import java.io.IOException;

import com.alibaba.fastjson.JSON;

import android.os.Handler;
import android.os.Looper;
import com.hongri.okhttpdemo.okhttp.exception.OkHttpException;
import com.hongri.okhttpdemo.okhttp.listener.DisposeDataHandler;
import com.hongri.okhttpdemo.okhttp.listener.IDisposeDataListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhongyao on 2019-07-20.
 */
public class CommonJsonCallback implements Callback {

    protected final String RESULT_CODE = "ecode"; // 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";
    protected final String COOKIE_STORE = "Set-Cookie"; // decide the server it

    /**
     * the java layer exception, do not same to the logic error
     */
    protected final int NETWORK_ERROR = -1; // the network relative error
    protected final int JSON_ERROR = -2; // the JSON relative error
    protected final int OTHER_ERROR = -3; // the unknow error

    private IDisposeDataListener mListener;
    private Class<?> mClass;
    private Handler mDeliverHandler;

    public CommonJsonCallback(DisposeDataHandler handler) {
        mListener = handler.mListener;
        mClass = handler.mClass;
        mDeliverHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        mDeliverHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(e);
            }
        });
    }

    @Override
    public void onResponse(Call call, final Response response) throws IOException {
        //此时依然在子线程中
        String result = "";
        if (response != null && response.body() != null) {
            result = response.body().string();
        }
        final String finalResult = result;
        mDeliverHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(finalResult);
            }
        });
    }

    private void handleResponse(String result) {
        if (result == null || result.equals("")) {
            mListener.onFailure(result);
            return;
        }
        try {
            JSONObject resultObj = new JSONObject(result);
            if (true/*resultObj.has(RESULT_CODE)*/) {
                if (true/*resultObj.optInt(RESULT_CODE) == RESULT_CODE_VALUE*/) {
                    if (mClass == null) {
                        mListener.onSuccess(resultObj);
                    } else {
                        Object obj = JSON.parseObject(resultObj.toString(), mClass);

                        if (obj == null) {
                            mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                        } else {
                            //正确处理了json数据，返回了业务所需的实体对象
                            mListener.onSuccess(obj);
                        }

                    }
                } else {
                    mListener.onFailure(resultObj);
                }
            }
        } catch (JSONException e) {
            mListener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
            e.printStackTrace();
        }

    }
}
