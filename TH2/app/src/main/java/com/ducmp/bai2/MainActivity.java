package com.ducmp.bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView txtText, txtInputSize;
    private EditText edtSize;
    private Button btnSave, btnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setSize();

    }

    public void init() {
        txtText = findViewById(R.id.txtText);
        txtInputSize = findViewById(R.id.txtInputSize);
        edtSize = findViewById(R.id.edtSize);
        btnSave = findViewById(R.id.btnSaveSize);
        btnRefresh = findViewById(R.id.btnRefresh);
        String size = Untils.getSize(this);
        if (size != null) {
            Toast.makeText(this, "Size ahihi" + size, Toast.LENGTH_SHORT).show();
            btnSave.setVisibility(View.INVISIBLE);
            edtSize.setVisibility(View.INVISIBLE);
            txtInputSize.setVisibility(View.INVISIBLE);
            txtText.setTextSize(Float.parseFloat(size));
        } else {
            Toast.makeText(this, "Size" + size, Toast.LENGTH_SHORT).show();
        }
    }

    public void setSize() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sizeInput = edtSize.getText().toString().trim();
                if(sizeInput != null || !sizeInput.equals(" ")){
                    Float size = Float.parseFloat(sizeInput);
                    txtText.setTextSize(size);
                    Untils.setSize(sizeInput, MainActivity.this);
                }else{
                    Toast.makeText(MainActivity.this, "Chưa nhập text size", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSave.setVisibility(View.VISIBLE);
                edtSize.setVisibility(View.VISIBLE);
                txtInputSize.setVisibility(View.VISIBLE);
                txtText.setTextSize(12);
                Untils.refreshSize(MainActivity.this);
                Toast.makeText(MainActivity.this, "Size: "+ Untils.getSize(MainActivity.this), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
