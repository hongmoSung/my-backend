package com.my.user.domain.pay.app;

import com.my.concert.domain.booking.domain.Booking;
import com.my.concert.domain.booking.domain.BookingService;
import com.my.token.domain.PayEndEvent;
import com.my.user.domain.pay.api.dto.ReqChargeDto;
import com.my.user.domain.pay.api.dto.ResPayDto;
import com.my.user.domain.pay.domain.PayService;
import com.my.token.domain.Token;
import com.my.token.domain.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@RequiredArgsConstructor
@Service
public class PayFacade {

	private final TokenService tokenService;

	private final PayService payService;

	private final BookingService bookingService;

	private final ApplicationEventPublisher eventPublisher;

	public ResPayDto getBalance(String authorization) {
		if (authorization == null || !authorization.contains("Bearer ")) {
			return new ResPayDto();
		}

		Token token = tokenService.decodeToken(authorization.replace("Bearer ", ""));
		BigInteger balance = payService.getBalanceByUserUuid(token.getUserUuid());
		return ResPayDto.builder().email(token.getEmail()).money(balance).build();
	}

	public void balanceRecharge(String authorization, ReqChargeDto reqChargeDto) {
		if (authorization == null || !authorization.contains("Bearer ")) {
			return;
		}

		Token token = tokenService.decodeToken(authorization.replace("Bearer ", ""));
		payService.rechargeMoney(token.getUserUuid(), reqChargeDto.getChargeAmount());
	}

	public void pay(String authorization, Long bookingId) {
		if (authorization == null || !authorization.contains("Bearer ")) {
			return;
		}

		Token token = tokenService.decodeToken(authorization.replace("Bearer ", ""));
		Booking booking = bookingService.getBooking(bookingId);
		payService.pay(token.getUserUuid(), booking);

		tokenService.changeDone(token);
		eventPublisher.publishEvent(new PayEndEvent());
	}

}
