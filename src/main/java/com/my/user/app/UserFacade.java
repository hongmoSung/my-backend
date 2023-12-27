package com.my.user.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.my.user.domain.User;
import com.my.user.domain.UserService;
import com.my.user.domain.pay.api.dto.PaymentDto;
import com.my.user.domain.pay.api.dto.ReqChargeDto;
import com.my.user.domain.pay.api.dto.ResPayDto;
import com.my.user.domain.pay.domain.PayService;
import com.my.user.domain.token.domain.Token;
import com.my.user.domain.token.domain.TokenService;
import java.math.BigInteger;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserFacade {

    private final UserService userService;
    private final TokenService tokenService;
    private final PayService payService;

    public String getToken(String email) throws JsonProcessingException {
        User user = userService.getUser(email);
        return tokenService.getTokenString(user);
    }


    public ResPayDto getBalance(String authorization) {
        if (authorization == null || !authorization.contains("Bearer ")) {
            return new ResPayDto();
        }

        Token token = tokenService.decodeToken(authorization.replace("Bearer ", ""));
        BigInteger balance = payService.getBalanceByUserUuid(token.getUserUuid());
        return ResPayDto.builder()
            .email(token.getEmail())
            .money(balance)
            .build();
    }

    public void balanceRecharge(String authorization, ReqChargeDto reqChargeDto) {
        if (authorization == null || !authorization.contains("Bearer ")) {
            return;
        }

        Token token = tokenService.decodeToken(authorization.replace("Bearer ", ""));
        payService.rechargeMoney(token.getUserUuid(), reqChargeDto.getChargeAmount());
    }

    public void pay(String authorization) {
        if (authorization == null || !authorization.contains("Bearer ")) {
            return;
        }

        Token token = tokenService.decodeToken(authorization.replace("Bearer ", ""));
        payService.pay(token.getUserUuid());
    }
}
