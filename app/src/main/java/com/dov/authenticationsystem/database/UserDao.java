package com.dov.authenticationsystem.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.dov.authenticationsystem.User;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("DELETE FROM user WHERE login = :login")
    void deleteById(String login);
}
