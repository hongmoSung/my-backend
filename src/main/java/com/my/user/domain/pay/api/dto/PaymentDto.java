package com.my.user.domain.pay.api.dto;

import java.math.BigInteger;
import lombok.Getter;

@Getter
public class PaymentDto {

    private BigInteger money = BigInteger.ZERO;

}
