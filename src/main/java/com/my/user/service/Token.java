package com.my.user.service;

import com.my.user.repo.UserEntity;
import java.util.UUID;
import lombok.Getter;

@Getter
public class Token {

    private String userUuid;
    private String email;
    private int number = 1;
    private Long expireAt;

    public Token(String email) {
        this.email = email;
    }

    public UserEntity toEntity() {
        return UserEntity.builder()
            .email(email)
            .build();
    }

    public void addTokenInfo(String userUuid, int number, Long expireAt) {
        this.userUuid = userUuid;
        this.number = number;
        this.expireAt = expireAt;
    }
}
