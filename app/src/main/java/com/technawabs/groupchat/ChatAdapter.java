package com.technawabs.groupchat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.technawabs.groupchat.pojo.UserInfo;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private List<UserInfo> userInfoList;

    public ChatAdapter(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.conversation_card, parent, false);
        return new ChatViewHolder(parent.getContext(), itemView);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        final UserInfo userInfo = userInfoList.get(position);
        holder.userName.setText(userInfo.getName() + "(" + userInfo.getUserName() + ")");
        holder.userMessage.setText(userInfo.getBody());
        final AtomicBoolean loaded = new AtomicBoolean();
        if (!userInfo.getImageUrl().isEmpty()) {
            Picasso.with(holder.context).load(userInfo.getImageUrl()).into(holder.userImage, new Callback() {
                @Override
                public void onSuccess() {
                    loaded.set(true);
                }

                @Override
                public void onError() {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return userInfoList.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        private TextView userName;
        private TextView userMessage;
        private ImageView userImage;

        public ChatViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            userName = (TextView) itemView.findViewById(R.id.user_name);
            userMessage = (TextView) itemView.findViewById(R.id.user_message);
            userImage = (ImageView) itemView.findViewById(R.id.user_image);
        }
    }

}
