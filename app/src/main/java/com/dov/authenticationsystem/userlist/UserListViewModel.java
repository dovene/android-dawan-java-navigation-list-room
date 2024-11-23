package com.dov.authenticationsystem.userlist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dov.authenticationsystem.User;
import com.dov.authenticationsystem.database.AppRoomDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserListViewModel extends AndroidViewModel {
    private final MutableLiveData<ArrayList<User>> _users = new MutableLiveData<>();
    public LiveData<ArrayList<User>> users = _users;


    public UserListViewModel(@NonNull Application application) {
        super(application);
    }

    public void getAllUsers() {
        new Thread(() -> {
            AppRoomDatabase db = AppRoomDatabase.getInstance(getApplication());
            _users.postValue(new ArrayList<>(db.userDao().getAll()));
        }).start();
    }

    public void deleteUser(String login) {
        new Thread(() -> {
            AppRoomDatabase db = AppRoomDatabase.getInstance(getApplication());
            db.userDao().deleteById(login);
            _users.postValue(new ArrayList<>(db.userDao().getAll()));
        }).start();
    }

}