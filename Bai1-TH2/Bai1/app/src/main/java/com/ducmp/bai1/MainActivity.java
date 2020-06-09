package com.ducmp.bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.img);
        String drawableLink = "ic_launcher_background";
        int a= R.drawable.ic_launcher_background;
        Drawable imgLink = getResources().getDrawable(a);
        img.setBackgroundDrawable(imgLink);
    }
}
