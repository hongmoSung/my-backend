package com.my.concert.app;

import com.my.concert.api.dto.ReqCreateConcertDto;
import com.my.concert.domain.Concert;
import com.my.concert.domain.ConcertService;
import com.my.concert.domain.seat.domain.Seat;
import com.my.concert.domain.seat.domain.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConcertFacade {

	private final ConcertService concertService;

	private final SeatService seatService;

	public void createThingsNeededForTheConcert(ReqCreateConcertDto dto) {
		Concert concert = dto.toConcert();
		List<Seat> seats = seatService.createSeatsByConcert(concert);
		concert.setSeats(seats);
		concertService.createConcert(concert);
	}

}
