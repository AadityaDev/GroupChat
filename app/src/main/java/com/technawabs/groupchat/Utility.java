package com.technawabs.groupchat;

import android.text.TextUtils;
import android.util.Log;

import com.technawabs.groupchat.constatnts.GroupChatAPI;
import com.technawabs.groupchat.network.RequestGenerator;
import com.technawabs.groupchat.network.RequestHandler;
import com.technawabs.groupchat.pojo.UserInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

public class Utility {

    private static final String TAG = "Utility";

    public static void initializeChatList(List<UserInfo> userInfoList, String match) {
        List<UserInfo> users = new ArrayList<UserInfo>();
        Request request = RequestGenerator.get(GroupChatAPI.GROUP_CHAT_URL);
        try {
            String response = RequestHandler.makeRequestAndValidate(request);
            JSONObject result = new JSONObject(response);
            for (int i = 0; i < result.getInt("count"); i++) {
                JSONArray messages = result.getJSONArray("messages");
                if (!messages.isNull(i)) {
                    JSONObject message = messages.getJSONObject(i);
                    Log.i(TAG, message.toString());
                    final UserInfo userInfo = new UserInfo();
                    userInfo.setName(message.getString("Name"));
                    userInfo.setBody(message.getString("body"));
                    userInfo.setUserName(message.getString("username"));
                    userInfo.setImageUrl(message.getString("image-url"));
                    users.add(userInfo);
                }

            }
            if (!TextUtils.isEmpty(match)) {
                List<UserInfo> userList = new ArrayList<UserInfo>();
                for (int i = 0; i < result.getInt("count"); i++) {
                    Log.i(TAG, "Matched " + match);
                    if (users.get(i).getName().equalsIgnoreCase(match)) {
                        userList.add(users.get(i));
                    }
                }
                userInfoList.addAll(userList);
            } else {
                userInfoList.addAll(users);
            }

        } catch (Exception e) {
            Log.i(TAG, e.getMessage());
        }
    }

}
