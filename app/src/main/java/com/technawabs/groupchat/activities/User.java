package com.technawabs.groupchat.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.technawabs.groupchat.R;
import com.technawabs.groupchat.Utility;
import com.technawabs.groupchat.adapter.ChatAdapter;
import com.technawabs.groupchat.constatnts.GroupChatAPI;
import com.technawabs.groupchat.customviews.ItemClickSupport;
import com.technawabs.groupchat.network.RequestGenerator;
import com.technawabs.groupchat.network.RequestHandler;
import com.technawabs.groupchat.pojo.UserInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

public class User extends AppCompatActivity {

    private final String TAG = this.getClass().getName();
    private RecyclerView recList;
    private LinearLayoutManager linearLayoutManager;
    private List<UserInfo> userInfoList;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        getSupportActionBar().setTitle(getIntent().getStringExtra("match").toUpperCase());

        recList = (RecyclerView) findViewById(R.id.group_chat_list);
        recList.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(linearLayoutManager);
        userInfoList = new ArrayList<UserInfo>();
        Utility.initializeChatList(userInfoList, getIntent().getStringExtra("match"));
        chatAdapter = new ChatAdapter(userInfoList);
        recList.setAdapter(chatAdapter);
        chatAdapter.notifyDataSetChanged();
    }
}
