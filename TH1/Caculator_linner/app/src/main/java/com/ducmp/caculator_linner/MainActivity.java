package com.ducmp.caculator_linner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtKetQua;
    Button btnPlus, btnSub, btnNhan, btnChia;
    EditText edtNumber1, edtNumber2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtNumber1 = findViewById(R.id.edtNumber1);
        edtNumber2 = findViewById(R.id.edtNumber2);
        txtKetQua = findViewById(R.id.txtKetQua);
        btnPlus = findViewById(R.id.btnPlus);
        btnSub = findViewById(R.id.btnSub);
        btnNhan = findViewById(R.id.btnNhan);
        btnChia = findViewById(R.id.btnChia);

     //  InfoScreen();
        formatData();
    }


    void formatData() {
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String so1 = edtNumber1.getText().toString().trim();
                String so2 =  edtNumber2.getText().toString().trim();
                final float number1 = Float.parseFloat(so1);
                final float number2 = Float.parseFloat(so2);
                txtKetQua.setText((number1 + number2) + "");
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String so1 = edtNumber1.getText().toString().trim();
                String so2 =  edtNumber2.getText().toString().trim();
                final float number1 = Float.parseFloat(so1);
                final float number2 = Float.parseFloat(so2);
                txtKetQua.setText((number1 - number2) + "");
            }
        });

        btnNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String so1 = edtNumber1.getText().toString().trim();
                String so2 =  edtNumber2.getText().toString().trim();
                final float number1 = Float.parseFloat(so1);
                final float number2 = Float.parseFloat(so2);
                txtKetQua.setText((number1 * number2)+ "");
            }
        });

        btnChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String so1 = edtNumber1.getText().toString().trim();
                String so2 =  edtNumber2.getText().toString().trim();
                final float number1 = Float.parseFloat(so1);
                final float number2 = Float.parseFloat(so2);
                txtKetQua.setText( (number1 / number2) + "");
            }
        });
    }


    void InfoScreen() {

        Display display =
                ((WindowManager) getSystemService(Context.WINDOW_SERVICE))
                        .getDefaultDisplay();

        switch (display.getRotation()) {
            case Surface.ROTATION_0:
                txtKetQua.setText("Màn hình LAYOUT đứng: 0°");
                break;

            case Surface.ROTATION_90:
                txtKetQua.setText("Màn hình LAYOUT ngang: 90°");
                break;

            case Surface.ROTATION_180:
                txtKetQua.setText("Màn hình LAYOUT ngang: 180°");
                break;

            case Surface.ROTATION_270:
                txtKetQua.setText("Màn hình LAYOUT đứng: 270°");
                break;
        }
    }
}
