package com.technawabs.groupchat.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.technawabs.groupchat.ChatAdapter;
import com.technawabs.groupchat.network.Factory;
import com.technawabs.groupchat.constatnts.GroupChatAPI;
import com.technawabs.groupchat.R;
import com.technawabs.groupchat.network.RequestGenerator;
import com.technawabs.groupchat.network.RequestHandler;
import com.technawabs.groupchat.pojo.UserInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();
    private RecyclerView recList;
    private LinearLayoutManager linearLayoutManager;
    private List<UserInfo> userInfoList;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Factory.setUpThreadPolicy();

        recList = (RecyclerView) findViewById(R.id.group_chat_list);
        recList.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(linearLayoutManager);
        userInfoList = new ArrayList<UserInfo>();
        initializeChatList();
        chatAdapter = new ChatAdapter(userInfoList);
        recList.setAdapter(chatAdapter);
        chatAdapter.notifyDataSetChanged();
    }

    public void initializeChatList() {
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
                    userInfoList.add(userInfo);
                }
            }

        } catch (Exception e) {
            Log.i(TAG, e.getMessage());
        }
    }
}
