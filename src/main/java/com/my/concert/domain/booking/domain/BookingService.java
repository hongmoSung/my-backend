package com.my.concert.domain.booking.domain;

import com.my.concert.domain.seat.domain.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookingService {

    private final BookingRepo bookingRepo;

    @Transactional
    public void booking(String userUuid, Seat seat) {
        Booking booking = Booking.builder()
                .seatId(seat.getId())
                .date(seat.getDate())
                .userUuid(userUuid)
                .build();
        bookingRepo.booking(booking);
    }

    public Booking getBooking(Long bookingId) {
        return bookingRepo.getBooking(bookingId);
    }
}
