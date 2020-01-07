package com.tglsol.profiles.adapter;

import android.content.Context;
import android.graphics.Color;
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
import java.util.List;

public class ProfileAdapter extends ArrayAdapter<Profile> {
    private Context context;
    private ArrayList<Profile> listProfile;
    private int curPostion = -1;

    public ProfileAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Profile> listProfile) {
        super(context, resource, listProfile);
        this.listProfile = listProfile;
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Profile profile = listProfile.get(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_profile, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvPhone = (TextView) convertView.findViewById(R.id.tvPhone);
        TextView tvAddress = (TextView) convertView.findViewById(R.id.tvAddress);
        // Populate the data into the template view using the data object
        tvName.setText(profile.getName());
        tvPhone.setText(profile.getPhone());
        tvAddress.setText(profile.getAddress());
        // Return the completed view to render on screen

        if (curPostion < 0) convertView.setBackgroundColor(Color.parseColor("#00000000"));
        else if (curPostion == position) convertView.setBackgroundColor(Color.parseColor("#50ABCDEF"));
        else convertView.setBackgroundColor(Color.parseColor("#00000000"));
        return convertView;
    }

    @Override
    public int getCount() {
        return listProfile.size();
    }

    public void setCurPostion(int curPostion) {
        this.curPostion = curPostion;
    }
}
