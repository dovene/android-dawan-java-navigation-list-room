package com.dov.authenticationsystem.register;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.dov.authenticationsystem.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText loginEditText, nameEditText, passwordEditText;
    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Inscription");
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        setViewItems();
        setObservers();
    }

    private void setObservers() {
        registerViewModel.registerSuccess.observe(this, success -> {
            if (success) {
                Toast.makeText(this, "Utilisateur enregistré avec succès", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Ce login est déjà utilisé", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setViewItems() {
        loginEditText = findViewById(R.id.login_edit);
        nameEditText = findViewById(R.id.name_edit);
        passwordEditText = findViewById(R.id.password_edit);
        findViewById(R.id.register_button).setOnClickListener(v -> register());
    }

    private void register() {
        String login = loginEditText.getText().toString();
        String name = nameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        registerViewModel.register(login, name, password);
    }
}