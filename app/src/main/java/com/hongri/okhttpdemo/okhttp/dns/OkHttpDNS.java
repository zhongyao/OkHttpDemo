package com.hongri.okhttpdemo.okhttp.dns;

import android.support.annotation.NonNull;
import android.util.Log;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import okhttp3.Dns;

/**
 * DNS
 */
public class OkHttpDNS implements Dns {

    private static final String TAG = "OkHttpDNS";
    private OkHttpDNS(){}

    public static OkHttpDNS getInstance() {
        return Holder.instance;
    }

    private static final class Holder {
        private static final OkHttpDNS instance = new OkHttpDNS();
    }
    @NonNull
    @Override
    public List<InetAddress> lookup(String hostname) throws UnknownHostException {
        Log.d(TAG, "lookup -- hostname" + hostname);
        //系统默认
        return Dns.SYSTEM.lookup(hostname);
    }
}
