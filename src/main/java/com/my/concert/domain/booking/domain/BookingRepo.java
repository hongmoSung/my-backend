package com.my.concert.domain.booking.domain;

import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo {

	void booking(Booking booking);

	Booking getBooking(Long bookingId);

}
