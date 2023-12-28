package com.my.user.domain.pay.infra;

import com.my.concert.domain.booking.domain.Booking;
import com.my.concert.domain.booking.infra.mysql.BookingEntity;
import com.my.concert.domain.booking.infra.mysql.BookingJpaRepo;
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

	private final BookingJpaRepo bookingJpaRepo;

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
	public void payMoney(String userUuid, Booking booking) {
		PayEntity payEntity = payJpaRepo.findByUserUserId(UUID.fromString(userUuid))
			.orElseThrow(() -> new RuntimeException("유저의 잔액조회에 실패하였습니다."));
		payEntity.payMoney(booking.getPrice());

		BookingEntity bookingEntity = bookingJpaRepo.findById(booking.getId())
			.orElseThrow(() -> new RuntimeException("예약 정보 조회에 실패하였습니다."));
		bookingEntity.paid();
	}

}
