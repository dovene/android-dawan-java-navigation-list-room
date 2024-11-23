package com.dov.authenticationsystem.userlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.dov.authenticationsystem.R;
import com.dov.authenticationsystem.User;

import java.util.ArrayList;

public class UserListRecyclerViewAdapter extends RecyclerView.Adapter<UserListViewHolder> {

    public interface OnItemAction {
        void delete(String login);
        void showDetails(User user);
    }

    private ArrayList<User> users;
    private OnItemAction onItemAction;

    public UserListRecyclerViewAdapter(ArrayList<User> users, OnItemAction onItemAction) {
        this.users = users;
        this.onItemAction = onItemAction;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.user_list_item, parent, false);
        return new UserListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListViewHolder holder, int position) {
        holder.bind(users.get(position), onItemAction);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}