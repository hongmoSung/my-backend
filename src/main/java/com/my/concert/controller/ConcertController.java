package com.my.concert.controller;

import com.my.concert.controller.dto.CreateConcertDto;
import com.my.concert.controller.dto.ResConcertDto;
import com.my.concert.controller.dto.ResRemainSeats;
import com.my.concert.controller.dto.ReserveDto;
import com.my.concert.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/concerts")
@RestController
public class ConcertController {

    private final ConcertService concertService;

    @PostMapping
    public ResponseEntity<Void> createConcert(@RequestBody CreateConcertDto dto) {
        concertService.createConcert(dto.toConcert());
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/{concertId}/available-dates")
    public ResponseEntity<ResConcertDto> getAvailableDates(@PathVariable("concertId") Long concertId) {
        return ResponseEntity.ok(concertService.getAvailableDates(concertId));
    }

    @GetMapping("/{concertId}/{date}/remain-seats")
    public ResponseEntity<ResRemainSeats> getRemainSeats(@PathVariable("concertId") Long concertId,
        @PathVariable("date") String date) {
        return ResponseEntity.ok(concertService.getRemainSeats(concertId, date));
    }

    @PostMapping("/reserve")
    public ResponseEntity<Void> reserve(@RequestBody ReserveDto reserveDto) {
        concertService.reserve(reserveDto);
        return ResponseEntity.created(null).build();
    }
}
