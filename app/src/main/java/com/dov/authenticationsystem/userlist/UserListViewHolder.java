package com.dov.authenticationsystem.userlist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dov.authenticationsystem.R;
import com.dov.authenticationsystem.User;


public class UserListViewHolder extends RecyclerView.ViewHolder {
    TextView loginTextView;
    TextView nameTextView;
    ImageView deleteImageView;

    public UserListViewHolder(@NonNull View itemView) {
        super(itemView);
        loginTextView = itemView.findViewById(R.id.login_textview);
        nameTextView = itemView.findViewById(R.id.name_textview);
        deleteImageView = itemView.findViewById(R.id.delete_iv);
    }

    public void bind(User user, UserListRecyclerViewAdapter.OnItemAction onItemAction) {
        loginTextView.setText(user.getLogin());
        nameTextView.setText(user.getName());
        deleteImageView.setOnClickListener(v -> {
            onItemAction.delete(user.getLogin());
        });
        itemView.setOnClickListener(v -> {
            onItemAction.showDetails(user);
        });
    }
}