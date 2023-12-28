package com.my.user.domain.pay.domain;

import com.my.concert.domain.booking.domain.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

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
	public void pay(String userUuid, Booking booking) {
		payRepo.payMoney(userUuid, booking);
	}

}
