package com.dov.authenticationsystem;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.dov.authenticationsystem.userlist.UserListActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText loginEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        loginEditText = findViewById(R.id.login_edit);
        passwordEditText = findViewById(R.id.password_edit);

        findViewById(R.id.login_button).setOnClickListener(v -> login());
        findViewById(R.id.register_button).setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void login() {
        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (AuthenticationManager.getInstance().login(login, password)) {
            Intent intent = new Intent(this, UserListActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Login ou mot de passe incorrect", Toast.LENGTH_LONG).show();
        }
    }
}
