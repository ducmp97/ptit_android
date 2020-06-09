package com.ducmp.bai1;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton btnEnglish, btnVietNam;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String lang = Untils.getLanguage(this);
        if(!lang.equals("")){
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_main);
        init();
        onClick();
    }
    public void init() {
        radioGroup = findViewById(R.id.radioGroup);
        btnEnglish = findViewById(R.id.btnEnglish);
        btnVietNam = findViewById(R.id.btnVietname);
        btnSave = findViewById(R.id.btnSave);
        String lang = Untils.getLanguage(this);
        if (lang.equals("en")) {
            setLocalLanguage("en");
            btnVietNam.setChecked(false);
            btnEnglish.setChecked(true);
        } else if (lang.equals("vi")) {
            btnEnglish.setChecked(false);
            btnVietNam.setChecked(true);
            setLocalLanguage("vi");
        }

        btnVietNam.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    setLocalLanguage("vi");
                    Untils.setLanguage("vi", MainActivity.this);
                    refeshLayout();

                }
            }
        });

        btnEnglish.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    setLocalLanguage("en");
                    Untils.setLanguage("en", MainActivity.this);
                    refeshLayout();
                }
            }
        });
    }

    public void setLocalLanguage(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources()
                .updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

    public void onClick() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnEnglish.isChecked()) {
                    Untils.setLanguage("en", MainActivity.this);
                    setLocalLanguage("en");
                } else if (btnVietNam.isChecked()) {
                    Untils.setLanguage("vi", MainActivity.this);
                    setLocalLanguage("vi");
                }
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void refeshLayout(){
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
}
