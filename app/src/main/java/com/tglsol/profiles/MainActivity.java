package com.tglsol.profiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tglsol.profiles.adapter.ProfileAdapter;
import com.tglsol.profiles.dialog.ProfileDetailDialog;
import com.tglsol.profiles.model.Profile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Context context;

    private int curPosition = -1;
    private ArrayList<Profile> listProfile;
    private ProfileAdapter profileAdapter;
    private ListView lvProfile;

    private Button btnAdd, btnInfor, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;

        SharedPreferences sharedPreferences = context.getSharedPreferences("Profile", Context.MODE_PRIVATE);
        String jsonProfile = sharedPreferences.getString("profile", null);
        if (jsonProfile == null) jsonProfile = readProfileFromAssets();
        listProfile = new Gson().fromJson(jsonProfile, new TypeToken<ArrayList<Profile>>() {}.getType());

        lvProfile = findViewById(R.id.lvProfiles);
        profileAdapter = new ProfileAdapter(this, R.layout.item_profile, listProfile);
        lvProfile.setAdapter(profileAdapter);
        profileAdapter.notifyDataSetChanged();

        lvProfile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                curPosition = i;
                profileAdapter.setCurPostion(curPosition);
                profileAdapter.notifyDataSetChanged();

            }
        });

        btnInfor = findViewById(R.id.btnInfor);
        btnInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (curPosition < 0) return;
                Profile profile = listProfile.get(curPosition);
                ProfileDetailDialog profileDetailDialog = new ProfileDetailDialog(context, profile, curPosition);
                profileDetailDialog.show();
            }
        });

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curPosition = -1;
                profileAdapter.setCurPostion(curPosition);
                profileAdapter.notifyDataSetChanged();
            }
        });

        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (curPosition < 0) return;
                listProfile.remove(curPosition);
                curPosition = -1;
                profileAdapter.setCurPostion(curPosition);
                profileAdapter.notifyDataSetChanged();
            }
        });
    }

    public String readProfileFromAssets() {
        String data = null;
        try {
            InputStream in = getAssets().open("json_friends.txt");
            int size = in.available();
            byte[] readding = new byte[size];
            in.read(readding);
            in.close();
            data = new String(readding);

        }catch (IOException ex){
            ex.printStackTrace();
        }
        return data;
    }

    public void OnProfileDetailDone() {
        profileAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Gson gson = new Gson();
        String jsonProfile = gson.toJson(
                listProfile,
                new TypeToken<ArrayList<Profile>>() {}.getType());
        SharedPreferences sharedPreferences = context.getSharedPreferences("Profile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("profile", jsonProfile);
        editor.apply();
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
