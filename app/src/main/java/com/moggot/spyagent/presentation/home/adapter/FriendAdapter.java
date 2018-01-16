package com.moggot.spyagent.presentation.home.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.moggot.spyagent.R;
import com.moggot.spyagent.data.model.UserInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewholder> {

    @NonNull
    private List<UserInfo> friendList;

    public FriendAdapter(@NonNull List<UserInfo> friendList) {
        this.friendList = friendList;
    }

    @Override
    public FriendViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.friend_item, parent, false);
        return new FriendViewholder(view);
    }

    @Override
    public void onBindViewHolder(FriendViewholder holder, int position) {
        holder.bind(friendList.get(position));
    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    static class FriendViewholder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_friend_photo)
        ImageView ivPhoto;
        @BindView(R.id.tv_friend_surname)
        TextView tvSurname;
        @BindView(R.id.tv_friend_name)
        TextView tvName;

        FriendViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(UserInfo user) {
            Glide.with(itemView.getContext()).load(user.getPhotoUrl()).into(ivPhoto);
            tvSurname.setText(user.getLastName());
            tvName.setText(user.getFirstName());
        }
    }
}
