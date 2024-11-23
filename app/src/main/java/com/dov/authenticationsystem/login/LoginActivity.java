package com.dov.authenticationsystem.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.dov.authenticationsystem.R;
import com.dov.authenticationsystem.register.RegisterActivity;
import com.dov.authenticationsystem.userlist.UserListActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText loginEditText, passwordEditText;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        setViewItems();
        setObservers();
    }

    private void setObservers() {
        loginViewModel.loginSuccess.observe(this, success -> {
            if (success) {
                Intent intent = new Intent(this, UserListActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Login ou mot de passe incorrect", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setViewItems() {
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
        loginViewModel.login(login, password);
    }
}
