package com.hongri.okhttpdemo.rxjava;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import rx.Subscription;

/**
 * @author：hongri
 * @date：3/30/22
 * @description：
 */
public class SubscriptionManager<T> implements ISubscription<T> {

    private Map<Object, List<Subscription>> mMap = new HashMap<>();

    private static SubscriptionManager sSubscriptionManager;

    public SubscriptionManager() {
    }

    public static synchronized SubscriptionManager getInstance() {
        if (sSubscriptionManager == null) {
            sSubscriptionManager = new SubscriptionManager();
        }
        return sSubscriptionManager;
    }

    @Override
    public void add(T tag, Subscription subscription) {
        List<Subscription> perPageList = mMap.get(tag);
        if (perPageList == null) {
            perPageList = new ArrayList<>();
            mMap.put(tag, perPageList);
        }

        perPageList.add(subscription);
        mMap.put(tag, perPageList);

    }

    @Override
    public void remove(T tag) {
        if (!mMap.isEmpty()) {
            List<Subscription> perPageList = mMap.get(tag);
            if (perPageList != null && perPageList.size() > 0) {
                mMap.remove(tag);
            }
        }

    }

    @Override
    public void removeAll() {
        if (!mMap.isEmpty()) {
            mMap.clear();
        }
    }

    @Override
    public void cancel(T tag) {
        if (!mMap.isEmpty()) {
            List<Subscription> perPageList = mMap.get(tag);
            if (perPageList != null && perPageList.size() > 0) {
                for (Subscription subscription : perPageList) {
                    if (subscription != null && !subscription.isUnsubscribed()) {
                        subscription.unsubscribe();
                    }
                }
                Log.d("SubscriptionManager", "tag--->" + tag);
                Log.d("SubscriptionManager", "perPageList--->" + perPageList.size());
                mMap.remove(tag);
            }
        }

    }

    @Override
    public void cancelAll() {
        if (!mMap.isEmpty()) {
            Set<Object> keys = mMap.keySet();
            for (Object apiKey : keys) {
                cancel((T) apiKey);
            }
        }
    }

    @Override
    public String getName(T tag) {
        return tag.getClass().getName();
    }
}
