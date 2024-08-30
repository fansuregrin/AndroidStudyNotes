package com.example.broadcastbestpractice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {
    private Button btnLogin;

    private EditText editAccount;

    private EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.login);
        editAccount = findViewById(R.id.account);
        editPassword = findViewById(R.id.password);

        btnLogin.setOnClickListener(v -> {
            String account = editAccount.getText().toString();
            String password = editPassword.getText().toString();
            if (account.equals("admin") && password.equals("123456")) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, R.string.login_failed, Toast.LENGTH_SHORT).show();
            }
        });
    }
}