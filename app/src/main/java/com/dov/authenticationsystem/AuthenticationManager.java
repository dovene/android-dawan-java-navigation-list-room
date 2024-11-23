package com.dov.authenticationsystem;

import java.util.ArrayList;

public class AuthenticationManager {
    private static AuthenticationManager instance;
    private ArrayList<User> userList = new ArrayList<>();

    private AuthenticationManager() {}

    public static AuthenticationManager getInstance() {
        if (instance == null) {
            instance = new AuthenticationManager();
        }
        return instance;
    }

    public boolean register(String login, String name, String password) {
        for (User user : userList) {
            if (user.getLogin().equals(login)) {
                return false; // Utilisateur déjà existant
            }
        }
        userList.add(new User(login, name, password));
        return true;
    }

    public boolean login(String login, String password) {
        for (User user : userList) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<User> getAllUsers() {
        return userList;
    }
}

