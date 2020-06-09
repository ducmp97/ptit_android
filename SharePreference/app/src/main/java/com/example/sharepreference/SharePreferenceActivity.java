package com.example.sharepreference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SharePreferenceActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preference);

        sharedPreferences = getSharedPreferences("shared", MODE_PRIVATE);

        int textSize = sharedPreferences.getInt("textSize", 0);
        String backgroundColor = sharedPreferences.getString("color", "");

        if (textSize == 0 || "".equals(backgroundColor)){
            Intent intent = new Intent(SharePreferenceActivity.this, SettingActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(SharePreferenceActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }
}
