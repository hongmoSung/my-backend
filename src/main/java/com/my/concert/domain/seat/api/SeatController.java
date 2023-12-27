package com.my.concert.domain.seat.api;

import com.my.concert.domain.seat.api.dto.ResEnableSeat;
import com.my.concert.domain.seat.api.dto.ResRemainSeats;
import com.my.concert.domain.seat.domain.SeatService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/concerts/{concertId}/remain-seats")
    public ResponseEntity<List<ResRemainSeats>> getRemainSeats(@PathVariable("concertId") Long concertId) {
        return ResponseEntity.ok(seatService.getRemainSeats(concertId));
    }

    @GetMapping("/concerts/{concertId}/remain-seats/{date}")
    public ResponseEntity<List<ResEnableSeat>> getRemainSeatsByDate(@PathVariable("concertId") Long concertId,
        @PathVariable("date") String date) {
        return ResponseEntity.ok(seatService.getRemainSeatsByDate(concertId, date));
    }
}
