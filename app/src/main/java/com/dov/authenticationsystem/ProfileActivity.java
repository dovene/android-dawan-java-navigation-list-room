package com.dov.authenticationsystem;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setViewItems();
    }

    private void setViewItems() {
        User user = (User) getIntent().getSerializableExtra("USER");

        if (user != null) {
            setTitle("Bonjour " + user.getName());
            ((TextView) findViewById(R.id.login_text)).setText(user.getLogin());
        }
    }
}
