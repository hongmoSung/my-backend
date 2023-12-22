package com.my.user.controller;

import com.my.user.service.Token;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TokenReqDto {

    private String email;

    public Token toToken() {
        return new Token(email);
    }
}
