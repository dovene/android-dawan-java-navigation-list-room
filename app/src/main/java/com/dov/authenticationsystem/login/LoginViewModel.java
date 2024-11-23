package com.dov.authenticationsystem.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dov.authenticationsystem.User;
import com.dov.authenticationsystem.database.AppRoomDatabase;

public class LoginViewModel extends AndroidViewModel {
    private final MutableLiveData<Boolean> _loginSuccess = new MutableLiveData<>();
    public LiveData<Boolean> loginSuccess = _loginSuccess;


    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void login(String login, String password) {
        new Thread(() -> {
            AppRoomDatabase db = AppRoomDatabase.getInstance(getApplication());
            for (User user : db.userDao().getAll()) {
                if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                    _loginSuccess.postValue(true);
                    return;
                }
            }
            _loginSuccess.postValue(false);
        }).start();
    }

}
