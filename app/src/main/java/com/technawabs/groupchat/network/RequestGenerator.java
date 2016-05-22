package com.technawabs.groupchat.network;

import android.support.annotation.NonNull;
import android.util.Log;

import com.technawabs.groupchat.constatnts.GroupChatAPI;

import okhttp3.Request;

public class RequestGenerator {

    private static final String TAG = "RequestGenerator";

    private static void addDefaultHeaders(@NonNull Request.Builder builder) {
        builder.header(GroupChatAPI.Headers.ACCEPT_KEY, GroupChatAPI.Headers.ACCEPT_VALUE);
        builder.removeHeader(GroupChatAPI.Headers.CAN_RENDER_HTML);
    }

    public static Request get(@NonNull String url) {
        Request.Builder builder = new Request.Builder().url(url);
        addDefaultHeaders(builder);
        Log.i(TAG, builder.build().toString());
        return builder.build();
    }

}
