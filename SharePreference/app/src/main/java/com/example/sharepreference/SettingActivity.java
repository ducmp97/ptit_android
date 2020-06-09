package com.example.sharepreference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {

    EditText etTextSize;
    EditText etColor;
    Button btnSubmit;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        etTextSize = findViewById(R.id.etTextSize);
        etColor = findViewById(R.id.etColor);
        btnSubmit = findViewById(R.id.btnSubmit);

        sharedPreferences = getSharedPreferences("shared", MODE_PRIVATE);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(etTextSize.getText().toString().trim()) || "".equals(etColor.getText().toString().trim())){
                    Toast.makeText(SettingActivity.this, "Please complete the form", Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("textSize", Integer.parseInt(etTextSize.getText().toString().trim()));
                    editor.putString("color", etColor.getText().toString());
                    editor.commit();

                    Intent intent = new Intent(SettingActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
