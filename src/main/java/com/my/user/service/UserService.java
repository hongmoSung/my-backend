package com.my.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.pay.repo.WalletEntity;
import com.my.user.controller.ResTokenDto;
import com.my.user.repo.UserEntity;
import com.my.user.repo.UserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.UUID;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    @Value("${jwt.secret-key}")
    private String key;

    private final UserRepo userRepo;

    @Transactional
    public ResTokenDto getToken(Token token) throws JsonProcessingException {
        UserEntity user = userRepo.findByEmail(token.getEmail())
            .orElseGet(token::toEntity);

        if (user.isWalletEmpty()) {
            WalletEntity wallet = new WalletEntity(user);
            user.addWallet(wallet);
        }

        userRepo.save(user);
        long expireAt = LocalDateTime.now().plusMinutes(5).toEpochSecond(ZoneOffset.UTC);

        token.addTokenInfo(user.getUserId().toString(), 1, expireAt);

        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(token);
        String tokenString = Jwts.builder()
            .signWith(getSecretKey())
            .content(content)
            .compact();

        return ResTokenDto.builder()
            .token(tokenString)
            .build();
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

        String userUuid = claims.get("userId", String.class);
        String email = claims.get("email", String.class);
        Integer number = claims.get("number", Integer.class);
        Long expireAt = claims.get("expireAt", Long.class);

        Token token = new Token(email);
        token.addTokenInfo(userUuid, number, expireAt);
        return token;
    }
}
