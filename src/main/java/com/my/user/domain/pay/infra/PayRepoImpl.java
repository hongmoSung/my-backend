package com.my.user.domain.pay.infra;

import com.my.user.domain.pay.domain.PayRepo;
import com.my.user.domain.pay.infra.mysql.PayEntity;
import com.my.user.domain.pay.infra.mysql.PayJpaRepo;
import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PayRepoImpl implements PayRepo {

    private final PayJpaRepo payJpaRepo;

    @Override
    public BigInteger getBalanceByUserUuid(String userUuid) {
        Optional<PayEntity> optionalPay = payJpaRepo.findByUserUserId(UUID.fromString(userUuid));
        if (optionalPay.isEmpty()) {
            return BigInteger.ZERO;
        }
        return optionalPay.get().getMoney();
    }

    @Override
    public void rechargeMoney(String userUuid, BigInteger chargeAmount) {
        PayEntity payEntity = payJpaRepo.findByUserUserId(UUID.fromString(userUuid))
            .orElseThrow(() -> new RuntimeException("유저의 잔액조회에 실패하였습니다."));
        payEntity.rechargeMoney(chargeAmount);
    }

    @Override
    public void payMoney(String userUuid) {
        PayEntity payEntity = payJpaRepo.findByUserUserId(UUID.fromString(userUuid))
            .orElseThrow(() -> new RuntimeException("유저의 잔액조회에 실패하였습니다."));
        payEntity.payMoney(0);
    }
}
