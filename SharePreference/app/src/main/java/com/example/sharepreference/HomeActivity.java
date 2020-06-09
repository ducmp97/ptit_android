package com.example.sharepreference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class HomeActivity extends AppCompatActivity {

    TextView tvHome;
    ConstraintLayout layoutHome;
    Button btnClearSetting;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvHome = findViewById(R.id.tvHome);
        layoutHome = findViewById(R.id.layoutHome);
        btnClearSetting = findViewById(R.id.btnClearSetting);

        sharedPreferences = getSharedPreferences("shared", MODE_PRIVATE);

        int textSize = sharedPreferences.getInt("textSize", 0);
        String color = sharedPreferences.getString("color", "");

        tvHome.setTextSize((float) textSize);
        layoutHome.setBackgroundColor(Color.parseColor(color));

        btnClearSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("textSize");
                editor.remove("color");
                editor.commit();

                Intent intent = new Intent(HomeActivity.this, SharePreferenceActivity.class);
                startActivity(intent);
            }
        });
    }
}
