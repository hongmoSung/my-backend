package com.my.user.domain.pay.domain;

import com.my.user.domain.pay.api.dto.PaymentDto;
import java.math.BigInteger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PayService {

    private final PayRepo payRepo;

    public BigInteger getBalanceByUserUuid(String uuid) {
        return payRepo.getBalanceByUserUuid(uuid);
    }

    @Transactional
    public void rechargeMoney(String userUuid, BigInteger chargeAmount) {
        payRepo.rechargeMoney(userUuid, chargeAmount);
    }

    @Transactional
    public void pay(String userUuid) {
        payRepo.payMoney(userUuid);
    }
}
