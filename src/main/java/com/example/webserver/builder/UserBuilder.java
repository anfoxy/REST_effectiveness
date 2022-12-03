package com.example.webserver.builder;

import com.example.webserver.model.User;

public class UserBuilder {

    private Long id;
    private String username;
    private String password;
    private String  code;

    private String userLN;

    private String userFN;

    public UserBuilder setUserLN(String userLN) {
        this.userLN = userLN;
        return this;
    }
    public UserBuilder setUserFN(String userFN) {
        this.userFN = userFN;
        return this;
    }

    public UserBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setCode(String code) {
        this.code = code;
        return this;
    }

    public User build() {
        return new User(id, username, password,code,userLN, userFN);
    }

}
