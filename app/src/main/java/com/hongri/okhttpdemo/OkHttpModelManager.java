package com.hongri.okhttpdemo;

/**
 * Created by zhongyao on 2019-07-20.
 */
public class OkHttpModelManager {

    public static OkHttpBaseModel mModel;

    public static OkHttpBaseModel newInstance(String modelName) {
        try {
            mModel = (OkHttpBaseModel)Class.forName(modelName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mModel;
    }

}
