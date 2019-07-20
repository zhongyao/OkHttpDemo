package com.hongri.okhttpdemo.okhttp.listener;

import java.util.ArrayList;

/**
 * Created by zhongyao on 2019-07-20.
 * 当需要专门处理Cookie时创建此回调接口
 */
public interface IDisposeHandleCookieListener extends IDisposeDataListener {

    void onCookie(ArrayList<String> cookieStrLists);
}
