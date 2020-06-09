package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ChessDetailActivity extends AppCompatActivity {

    ImageView ivChessImage;
    TextView tvDescriptionChess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess_detail);

        ivChessImage = findViewById(R.id.ivChessImage);
        tvDescriptionChess = findViewById(R.id.tvDescriptionChess);

        Intent intent = getIntent();

        int image = intent.getIntExtra("image", 0);
        String description = intent.getStringExtra("description");

        if (image != 0){
            ivChessImage.setImageResource(image);
        }
        tvDescriptionChess.setText(description);
    }
}
