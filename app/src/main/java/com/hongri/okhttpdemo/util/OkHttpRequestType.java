package com.hongri.okhttpdemo.util;

/**
 * Created by zhongyao on 2019-07-20.
 */

public class OkHttpRequestType {

    public enum RequestType {

        GET(1),

        POST(2),

        UPLOAD(3),

        DOWNLOAD_IMAGE(4);

        private int value;

        RequestType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }
}

