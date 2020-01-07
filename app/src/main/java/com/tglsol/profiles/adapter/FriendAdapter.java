package com.tglsol.profiles.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tglsol.profiles.R;
import com.tglsol.profiles.model.Friend;
import com.tglsol.profiles.model.Profile;

import java.util.ArrayList;

public class FriendAdapter extends ArrayAdapter<Friend> {
    private Context context;
    private ArrayList<Friend> listFriend;

    public FriendAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Friend> listFriend) {
        super(context, resource, listFriend);
        this.listFriend = listFriend;
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Friend friend = listFriend.get(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_friend, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        tvName.setText(friend.getName());
        return convertView;
    }

    @Override
    public int getCount() {
        return listFriend.size();
    }
}
