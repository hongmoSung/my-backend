package com.my.user.domain.token.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.user.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import javax.crypto.SecretKey;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TokenService {

    @Value("${jwt.secret-key}")
    private String key;

    private final TokenRepo tokenRepo;

    public Token getToken(User user) throws JsonProcessingException {

        Token token = Token.builder()
                .userUuid(user.getUserId())
                .email(user.getEmail())
                .build();

        String tokenString = getTokenString(token);
        token.addTokenString(tokenString);

        return token;
    }

    private String getTokenString(Token token) throws JsonProcessingException {
        String content = new ObjectMapper().writeValueAsString(token);
        return Jwts.builder()
            .signWith(getSecretKey())
            .content(content)
            .compact();
    }

    private SecretKey getSecretKey() {
        String encodeToString = Base64.getEncoder().encodeToString(key.getBytes());
        return Keys.hmacShaKeyFor(encodeToString.getBytes());
    }

    public Token decodeToken(String tokenString) {
        Claims claims = Jwts.parser()
            .verifyWith(getSecretKey())
            .build()
            .parseSignedClaims(tokenString).getPayload();

        String userUuid = claims.get("userUuid", String.class);
        String email = claims.get("email", String.class);
        Integer number = claims.get("number", Integer.class);
        Long expireAt = claims.get("expireAt", Long.class);
        return new Token(userUuid, email);
    }

    @Transactional
    public void saveToken(Token token) {
        tokenRepo.saveToken(token.toEntity());
    }
}
