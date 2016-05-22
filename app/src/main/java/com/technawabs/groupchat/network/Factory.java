package com.technawabs.groupchat.network;

import android.os.StrictMode;

import okhttp3.OkHttpClient;

public class Factory {

    private static final String TAG = "Factory";

    private static final Object LOCK = new Object();

    private static OkHttpClient mOkHttpClient;

    public static OkHttpClient getOkHTTPClient() {
        synchronized (LOCK) {
            if (mOkHttpClient == null) {
                mOkHttpClient = new OkHttpClient();
            }
        }
        return mOkHttpClient;
    }

    public static void setUpThreadPolicy() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


}
