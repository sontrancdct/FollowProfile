package com.tglsol.profiles.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.tglsol.profiles.R;
import com.tglsol.profiles.model.Friend;

public class FriendDetailDialog extends Dialog {
    private EditText tvFriendName;
    private Button btnDone;

    public FriendDetailDialog(@NonNull Context context, final Friend friend) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_dialog_friend_detail);

        tvFriendName = findViewById(R.id.tvFriendName);
        tvFriendName.setText(friend.getName());

        btnDone = findViewById(R.id.btnFriendDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friend.setName(tvFriendName.getText().toString());
                dismiss();
            }
        });
    }
}
