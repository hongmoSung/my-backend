package com.my.concert.domain.seat.domain;

import com.my.concert.domain.seat.api.dto.ResEnableSeat;
import com.my.concert.domain.seat.api.dto.ResRemainSeats;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepo {

    List<ResRemainSeats> getEnableReserveDays(Long concertId);

    List<ResEnableSeat> getRemainSeatsByDate(Long concertId, String date);
}
