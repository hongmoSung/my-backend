package com.my.pay.controller;

import java.math.BigInteger;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReqChargeDto {
    private final BigInteger chargeAmount = new BigInteger("0");
}
