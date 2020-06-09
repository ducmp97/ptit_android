package com.ducmp.bai2.activity;

import android.content.Intent;
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
import com.ducmp.bai2.untils.Untils;

public class LoginActivity extends AppCompatActivity {


    private EditText edtUsername, edtPassword;
    private Button btnShowPass, btnLogin, btnRegister;
    private Boolean isShow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String username = Untils.getUser(getBaseContext());
        if (!username.equals("")) {
            goToMain();
        } else {
            setContentView(R.layout.activity_login);
            init();
            showPass();
            onClick();
        }
    }

    public void init() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnShowPass = findViewById(R.id.btnShowPass);
        btnRegister = findViewById(R.id.btnRegister);


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

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegister();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                if (username != null || !username.equals("") || password != null || !password.equals(" ")) {
                    checkLogin(username, password);
                } else {
                    Toast.makeText(LoginActivity.this, "Tài khoản haowcj mật khẩu không bỏ trống", Toast.LENGTH_SHORT).show();
                }
            }
        });
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

    public void goToRegister() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void goToMain() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void checkLogin(String username, String password) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
        Account account = databaseHelper.checkLogin(username, password);
        if (account != null) {
            Untils.setUser(account.getName(), getBaseContext());
            goToMain();
        } else {
            Toast.makeText(this, "Tài khoản hoặc mật khẩu sai", Toast.LENGTH_SHORT).show();
        }
    }
}
