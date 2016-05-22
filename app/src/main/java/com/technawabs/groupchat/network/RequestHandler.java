package com.technawabs.groupchat.network;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestHandler {

    public static String makeRequestAndValidate(Request request) throws JSONException, IOException, Exception {
        return doRequest(request, true);
    }

    private static String doRequest(Request request, boolean doValidate) throws IOException, JSONException, Exception {
        Log.i("HTTP", request.method() + " : " + request.url());

        OkHttpClient httpClient = Factory.getOkHTTPClient();
        Response response;
        String body = new String();
        response = httpClient.newCall(request).execute();
        if (response == null) {
            return null;
        }
        body = response.body().string();
        Log.i("HTTP", response.code() + " : " + body);

        if (!response.isSuccessful()) {
            return body;
        } else {
            if (doValidate) {
                validateResponse(body);
            }
            return body;
        }
    }

    private static void validateResponse(String body) throws Exception, JSONException {
        JSONObject response;
        try {
            response = new JSONObject(body);
        } catch (JSONException ignored) {
            throw new JSONException(body);

        }
    }

}
