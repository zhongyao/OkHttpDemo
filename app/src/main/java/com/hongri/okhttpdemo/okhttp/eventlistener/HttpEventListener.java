package com.hongri.okhttpdemo.okhttp.eventlistener;

import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.Protocol;
import okhttp3.Request;

/**
 * 网络请求事件监听
 */
public class HttpEventListener extends EventListener {
    private static final String TAG = "HttpEventListener";
    /**
     * 每次请求的标识
     */
    private final long callId;

    /**
     * 每次请求的开始时间，单位纳秒
     */
    private final long callStartNanos;

    private StringBuilder sbLog;

    /**
     * 自定义EventListener工厂
     */
    public static final Factory FACTORY = new Factory() {
        final AtomicLong nextCallId = new AtomicLong(1L);

        @Override
        public EventListener create(Call call) {
            long callId = nextCallId.getAndIncrement();
            return new HttpEventListener(callId, call.request().url(), System.nanoTime());
        }
    };

    public HttpEventListener(long callId, HttpUrl url, long callStartNanos) {
        this.callId = callId;
        this.callStartNanos = callStartNanos;
        this.sbLog = new StringBuilder(url.toString()).append(" ").append(callId).append(":");
    }

    /**
     * 当一个Call（代表一个请求）被同步执行或被添加异步队列中时
     * @param call
     */
    @Override
    public void callStart(Call call) {
        super.callStart(call);
        recordEventLog("callStart");
    }

    @Override
    public void dnsStart(Call call, String domainName) {
        super.dnsStart(call, domainName);
        Log.d(TAG, "dnsStart -- domainName:" + domainName);
        recordEventLog("dnsStart");
    }

    @Override
    public void dnsEnd(Call call, String domainName, List<InetAddress> inetAddressList) {
        super.dnsEnd(call, domainName, inetAddressList);
        Log.d(TAG, "dnsEnd -- domainName:" +domainName + " inetAddressList:" + inetAddressList.toString());
        recordEventLog("dnsEnd");
    }

    @Override
    public void callEnd(Call call) {
        super.callEnd(call);
        recordEventLog("callEnd");
    }

    @Override
    public void callFailed(Call call, IOException ioe) {
        super.callFailed(call, ioe);
        recordEventLog("callFailed");
    }

    @Override
    public void connectStart(Call call, InetSocketAddress inetSocketAddress, Proxy proxy) {
        super.connectStart(call, inetSocketAddress, proxy);
        recordEventLog("connectStart");
    }

    @Override
    public void connectEnd(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol) {
        super.connectEnd(call, inetSocketAddress, proxy, protocol);
        recordEventLog("connectEnd");
    }

    @Override
    public void connectionAcquired(Call call, Connection connection) {
        super.connectionAcquired(call, connection);
        recordEventLog("connectionAcquired");
    }

    @Override
    public void requestHeadersStart(Call call) {
        super.requestHeadersStart(call);
        recordEventLog("requestHeadersStart");
    }

    @Override
    public void requestHeadersEnd(Call call, Request request) {
        super.requestHeadersEnd(call, request);
        recordEventLog("requestHeadersEnd");
    }

    @Override
    public void requestBodyStart(Call call) {
        super.requestBodyStart(call);
        recordEventLog("requestBodyStart");
    }

    @Override
    public void responseBodyEnd(Call call, long byteCount) {
        super.responseBodyEnd(call, byteCount);
        recordEventLog("requestBodyEnd");
    }

    private void recordEventLog(String name) {
        long elapseNanos = System.nanoTime() - callStartNanos;
        sbLog.append(String.format(Locale.CHINA, "%.3f-%s", elapseNanos / 1000000000d, name)).append(";");
        Log.d(TAG, name + " --> " + sbLog.toString());
        if (name.equalsIgnoreCase("callEnd") || name.equalsIgnoreCase("callFailed")) {
            //打印出每个步骤的时间点
//            Log.d(TAG, sbLog.toString());
        }
    }
}
