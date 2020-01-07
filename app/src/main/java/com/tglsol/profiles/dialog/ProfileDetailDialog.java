package com.tglsol.profiles.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.tglsol.profiles.MainActivity;
import com.tglsol.profiles.R;
import com.tglsol.profiles.adapter.FriendAdapter;
import com.tglsol.profiles.model.Friend;
import com.tglsol.profiles.model.Profile;

import java.util.ArrayList;

public class ProfileDetailDialog extends Dialog {
    private MainActivity mainActivity;
    private EditText tvName, tvPhone, tvAddress;
    private Button btnAddFriend, btnDeleteFriend, btnDone;
    private ListView lvFriend;
    private FriendAdapter friendAdapter;

    public ProfileDetailDialog(@NonNull final Context context, final Profile profile, final int position) {
        super(context);
        mainActivity = (MainActivity) context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_dialog_profile_detail);

        tvName = findViewById(R.id.tvName);
        tvPhone = findViewById(R.id.tvPhone);
        tvAddress = findViewById(R.id.tvAddress);
        lvFriend = findViewById(R.id.lvFriend);
        btnAddFriend = findViewById(R.id.btnAddFriend);
        btnAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnDeleteFriend = findViewById(R.id.btnDeleteFriend);
        btnDeleteFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnDone = findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile.setName(tvName.getText().toString());
                profile.setPhone(tvPhone.getText().toString());
                profile.setAddress(tvAddress.getText().toString());
                mainActivity.OnProfileDetailDone();
                dismiss();
            }
        });

        tvName.setText(profile.getName());
        tvPhone.setText(profile.getPhone());
        tvAddress.setText(profile.getAddress());

        friendAdapter = new FriendAdapter(context, R.layout.item_friend, profile.getFriends());
        lvFriend.setAdapter(friendAdapter);
        friendAdapter.notifyDataSetChanged();

        lvFriend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Friend friend = profile.getFriends().get(i);
                FriendDetailDialog friendDetailDialog = new FriendDetailDialog(context, friend);
                friendDetailDialog.show();
                friendDetailDialog.setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        friendAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
