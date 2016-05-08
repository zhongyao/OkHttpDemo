package com.hongri.okhttpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 *
 */
public class MainActivity extends AppCompatActivity {
    public static String YAO = "yao";

    private String url = "http://g.hiphotos.baidu.com/image/pic/item/adaf2edda3cc7cd96bf48f6f3c01213fb90e91af.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(YAO,"onCreate");

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    execute();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(YAO,"onResume");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void execute() throws Exception{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()){
            Log.d(YAO,response.code()+"");
        }else{
            Log.d(YAO,"error");
        }
    }

}
