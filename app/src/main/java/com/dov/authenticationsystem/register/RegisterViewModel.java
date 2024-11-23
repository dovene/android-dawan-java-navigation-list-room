package com.dov.authenticationsystem.register;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dov.authenticationsystem.User;
import com.dov.authenticationsystem.database.AppRoomDatabase;

public class RegisterViewModel extends AndroidViewModel {
    private final MutableLiveData<Boolean> _registerSuccess = new MutableLiveData<>();
    public LiveData<Boolean> registerSuccess = _registerSuccess;


    public RegisterViewModel(@NonNull Application application) {
        super(application);
    }

    public void register(String login, String name, String password) {
        new Thread(() -> {
            AppRoomDatabase db = AppRoomDatabase.getInstance(getApplication());
            for (User user : db.userDao().getAll()) {
                if (user.getLogin().equals(login)) {
                     _registerSuccess.postValue(false);
                     return;
                }
            }
            db.userDao().insert(new User(login, name, password));
            _registerSuccess.postValue(true);
        }).start();
    }

}
