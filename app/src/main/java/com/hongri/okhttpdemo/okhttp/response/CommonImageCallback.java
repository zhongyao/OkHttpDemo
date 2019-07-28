package com.hongri.okhttpdemo.okhttp.response;

import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.hongri.okhttpdemo.okhttp.exception.OkHttpException;
import com.hongri.okhttpdemo.okhttp.listener.DisposeDataHandler;
import com.hongri.okhttpdemo.okhttp.listener.IDisposeDownloadListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zhongyao on 2019-07-28.
 */
public class CommonImageCallback implements Callback {

    protected final int NETWORK_ERROR = -1;
    protected final int IO_ERROR = -2;
    protected final String EMPTY_MSG = "";
    /**
     * 将其它线程的数据转发到UI线程
     */
    private static final int PROGRESS_MESSAGE = 0x01;
    private Handler mDeliveryHandler;
    private IDisposeDownloadListener mListener;
    private String mFilePath;
    private int mProgress;

    public CommonImageCallback(DisposeDataHandler handle) {
        this.mListener = (IDisposeDownloadListener)handle.mListener;
        this.mFilePath = handle.mSource;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case PROGRESS_MESSAGE:
                        mListener.onProgress((int)msg.obj);
                        break;
                }
            }
        };
    }

    @Override
    public void onFailure(Call call, final IOException ioexception) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROR, ioexception));
            }
        });
    }

    @Override
    public void onResponse(Call call, final Response response) throws IOException {

        if (response.body() == null) {
            return;
        }
        //One thing to remember with stream : Reading or printing the outputStream will close it
        final InputStream inputStream = response.body().byteStream();
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                if (response.body() != null) {
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    mListener.onSuccess(bitmap);
                }

            }
        });
    }
}
