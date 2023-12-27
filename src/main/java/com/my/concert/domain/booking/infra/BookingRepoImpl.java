package com.my.concert.domain.booking.infra;

import com.my.concert.domain.booking.domain.Booking;
import com.my.concert.domain.booking.domain.BookingRepo;
import com.my.concert.domain.booking.infra.mysql.BookingEntity;
import com.my.concert.domain.booking.infra.mysql.BookingJpaRepo;
import com.my.concert.domain.seat.infra.mysql.SeatEntity;
import com.my.concert.domain.seat.infra.mysql.SeatJpaRepo;
import com.my.user.infra.UserEntity;
import com.my.user.infra.mysql.UserJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class BookingRepoImpl implements BookingRepo {

    private final BookingJpaRepo bookingJpaRepo;
    private final UserJpaRepo userJpaRepo;
    private final SeatJpaRepo seatJpaRepo;

    @Override
    public void booking(Booking booking) {

        SeatEntity seatEntity = seatJpaRepo.findById(booking.getSeatId())
                .orElseThrow(() -> new RuntimeException("시트를 찾을 수 없습니다."));
        seatEntity.reserveSeat();

        UserEntity userEntity = userJpaRepo.findById(UUID.fromString(booking.getUserUuid()))
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        BookingEntity bookingEntity = BookingEntity.builder()
                .seat(seatEntity)
                .user(userEntity)
                .date(seatEntity.getDate())
                .build();

        bookingJpaRepo.save(bookingEntity);
    }

    @Override
    public Booking getBooking(Long bookingId) {
        BookingEntity bookingEntity = bookingJpaRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("예약 정보를 찾을 수 없습니다."));

        Booking booking = Booking.builder()
                .id(bookingEntity.getId())
                .date(bookingEntity.getDate())
                .userUuid(bookingEntity.getUser().getUserId().toString())
                .seatId(bookingEntity.getSeat().getId())
                .price(bookingEntity.getSeat().getPrice())
                .build();
        return booking;
    }
}
