package com.dov.authenticationsystem.userlist;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dov.authenticationsystem.AuthenticationManager;
import com.dov.authenticationsystem.ProfileActivity;
import com.dov.authenticationsystem.R;
import com.dov.authenticationsystem.User;

public class UserListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserListRecyclerViewAdapter userListRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        setTitle("Liste des utilisateurs");
        setViewItems();
    }


    private void setViewItems() {
        recyclerView = findViewById(R.id.user_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userListRecyclerViewAdapter = new UserListRecyclerViewAdapter(AuthenticationManager.getInstance().getAllUsers(), new UserListRecyclerViewAdapter.OnItemAction() {
            @Override
            public void delete(String login) {
                AuthenticationManager.getInstance().deleteUser(login);
                userListRecyclerViewAdapter.setUsers(AuthenticationManager.getInstance().getAllUsers());
            }

            @Override
            public void showDetails(User user) {
                Intent intent = new Intent(UserListActivity.this, ProfileActivity.class);
                intent.putExtra("USER", user);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(userListRecyclerViewAdapter);
    }
}
