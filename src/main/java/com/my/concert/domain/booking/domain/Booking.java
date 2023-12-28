package com.my.concert.domain.booking.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Booking {

	private Long id;

	private LocalDate date;

	private LocalDate timeStamp;

	private String userUuid;

	private Long seatId;

	private int price;

	@Builder
	public Booking(Long id, LocalDate date, String userUuid, Long seatId, int price) {
		this.id = id;
		this.date = date;
		this.userUuid = userUuid;
		this.seatId = seatId;
		this.timeStamp = LocalDate.now();
		this.price = price;
	}

}
