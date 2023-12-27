package com.my.concert.domain.seat.domain;

import com.my.concert.domain.Concert;
import com.my.concert.domain.seat.api.dto.ResEnableSeat;
import com.my.concert.domain.seat.api.dto.ResRemainSeats;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SeatService {

    private final SeatRepo seatRepo;

    public List<Seat> createSeatsByConcert(Concert concert) {

        int days = concert.getConcertPeriodDays();
        LocalDate startDate = concert.getStartDate();

        List<Seat> seats = new ArrayList<>();
        IntStream.range(0, days).forEach(day -> {
            LocalDate seatDate = startDate.plusDays(day);
            IntStream.rangeClosed(1, 50).forEach(no -> {
                Seat seat = new Seat(no, seatDate);
                seats.add(seat);
            });
        });
        return seats;
    }

    public List<ResRemainSeats> getRemainSeats(Long concertId) {
        return seatRepo.getEnableReserveDays(concertId);
    }

    public List<ResEnableSeat> getRemainSeatsByDate(Long concertId, String date) {
        return seatRepo.getRemainSeatsByDate(concertId, date);
    }

    public Seat getSeat(Long seatId) {
        return seatRepo.getSeat(seatId);
    }
}
