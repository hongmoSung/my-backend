package com.my.concert.domain.seat.domain;

import com.my.concert.domain.seat.api.dto.ResEnableSeat;
import com.my.concert.domain.seat.api.dto.ResRemainSeats;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepo {

    List<ResRemainSeats> getEnableReserveDays(Long concertId);

    List<ResEnableSeat> getRemainSeatsByDate(Long concertId, String date);

    Seat getSeat(Long seatId);
}
