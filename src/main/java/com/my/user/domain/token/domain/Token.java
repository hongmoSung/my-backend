package com.my.user.domain.token.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import javax.crypto.SecretKey;
import lombok.Getter;

@Getter
public class Token {


    private String userUuid;
    private String email;
    private int number;
    private Long expireAt;

    public Token(String userUuid, String email) {
        this.userUuid = userUuid;
        this.email = email;
        this.expireAt = LocalDateTime.now().plusMinutes(5).toEpochSecond(ZoneOffset.UTC);
    }
}
