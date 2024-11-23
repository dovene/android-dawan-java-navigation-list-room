package com.dov.authenticationsystem.userlist;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dov.authenticationsystem.AuthenticationManager;
import com.dov.authenticationsystem.ProfileActivity;
import com.dov.authenticationsystem.R;
import com.dov.authenticationsystem.User;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserListRecyclerViewAdapter userListRecyclerViewAdapter;
    private UserListViewModel userListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        setTitle("Liste des utilisateurs");
        userListViewModel = new ViewModelProvider(this).get(UserListViewModel.class);
        userListViewModel.getAllUsers();
        setViewItems();
        setObservers();
    }

    private void setObservers() {
        userListViewModel.users.observe(this, users -> {
            userListRecyclerViewAdapter.setUsers(users);
        });
    }


    private void setViewItems() {
        recyclerView = findViewById(R.id.user_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userListRecyclerViewAdapter = new UserListRecyclerViewAdapter(
                new ArrayList<>(), // Start with empty list, will be updated by observer
                new UserListRecyclerViewAdapter.OnItemAction() {
                    @Override
                    public void delete(String login) {
                        userListViewModel.deleteUser(login);
                    }

                    @Override
                    public void showDetails(User user) {
                        Intent intent = new Intent(UserListActivity.this, ProfileActivity.class);
                        intent.putExtra("USER", user);
                        startActivity(intent);
                    }
                }
        );
        recyclerView.setAdapter(userListRecyclerViewAdapter);
    }
}
