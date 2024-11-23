package com.dov.authenticationsystem;

import java.io.Serializable;

public class User implements Serializable {
    private String login;
    private String name;
    private String password;

    public User(String login, String name, String password) {
        this.login = login;
        this.name = name;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}

