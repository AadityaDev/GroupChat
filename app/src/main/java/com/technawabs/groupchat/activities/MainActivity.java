package com.technawabs.groupchat.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.technawabs.groupchat.Utility;
import com.technawabs.groupchat.adapter.ChatAdapter;
import com.technawabs.groupchat.customviews.ItemClickSupport;
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
        Utility.initializeChatList(userInfoList, "");
        chatAdapter = new ChatAdapter(userInfoList);
        recList.setAdapter(chatAdapter);
        chatAdapter.notifyDataSetChanged();
        ItemClickSupport.addTo(recList).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(MainActivity.this, User.class);
                intent.putExtra("match", chatAdapter.getName(position));
                startActivity(intent);
            }
        });
    }
}
