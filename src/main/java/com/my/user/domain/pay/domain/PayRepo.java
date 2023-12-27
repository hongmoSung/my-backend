package com.my.user.domain.pay.domain;

import java.math.BigInteger;

import com.my.concert.domain.booking.domain.Booking;
import org.springframework.stereotype.Repository;

@Repository
public interface PayRepo {

    BigInteger getBalanceByUserUuid(String userUuid);

    void rechargeMoney(String userUuid, BigInteger chargeAmount);

    void payMoney(String userUuid, Booking price);
}
