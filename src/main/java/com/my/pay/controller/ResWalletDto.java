package com.my.pay.controller;

import java.math.BigInteger;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResWalletDto {

    private String email;
    private BigInteger money;

    @Builder
    public ResWalletDto(String email, BigInteger money) {
        this.email = email;
        this.money = money;
    }
}
