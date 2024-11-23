package com.dov.authenticationsystem.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.dov.authenticationsystem.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppRoomDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    // Singleton instance
    private static AppRoomDatabase instance;

    public static AppRoomDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppRoomDatabase.class, "user_database")
                    .build();
        }
        return instance;
    }
}