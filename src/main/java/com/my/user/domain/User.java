package com.my.user.domain;

import java.util.UUID;
import lombok.Getter;

@Getter
public class User {

    private String userId;
    private String email;

    public User(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }
}
