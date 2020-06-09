package com.ducmp.bai2.activity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ducmp.bai2.R;
import com.ducmp.bai2.database.DatabaseHelper;
import com.ducmp.bai2.model.Account;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword, edtAge, edtName, edtPhone, edtEmail, edtAddress;
    private Button btnSave, btnClear, btnShowPass;
    private Boolean isShow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        showPass();
        onClick();
    }

    public void init() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
        edtAddress = findViewById(R.id.edtAddress);
        btnSave = findViewById(R.id.btnSave);
        btnClear = findViewById(R.id.btnClear);
        btnShowPass = findViewById(R.id.btnShowPass);
    }

    public void onClick() {
        btnShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow) {
                    isShow = false;
                } else {
                    isShow = true;
                }
                showPass();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetText();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkValid()) {
                    String username = edtUsername.getText().toString().trim();
                    String password = edtPassword.getText().toString().trim();
                    String name = edtName.getText().toString().trim();
                    String phone = edtPhone.getText().toString().trim();
                    String email = edtEmail.getText().toString().trim();
                    String address = edtAddress.getText().toString().trim();
                    int age = Integer.parseInt(edtAge.getText().toString().trim());
                    Account account = new Account(name, phone, address, age, email, username, password);
                    DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
                    databaseHelper.register(account);
                    Toast.makeText(getBaseContext(), "Thêm dữ liệu thành công", Toast.LENGTH_SHORT).show();
                    resetText();
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Data is require", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean checkValid() {
        if (!edtUsername.equals("") && !edtPassword.equals("") &&
                !edtName.equals("") && !edtAge.equals("") &&
                !edtPhone.equals("") && !edtEmail.equals("") &&
                !edtAddress.equals("")) {
            return true;
        }
        return false;
    }

    public void showPass() {
        if (isShow) {
            btnShowPass.setBackgroundResource(R.drawable.eye);
            edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            btnShowPass.setBackgroundResource(R.drawable.hide);
            edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    public void resetText() {
        edtUsername.getText().clear();
        edtPassword.getText().clear();
        edtName.getText().clear();
        edtAge.getText().clear();
        edtPhone.getText().clear();
        edtEmail.getText().clear();
        edtAddress.getText().clear();
    }


}
