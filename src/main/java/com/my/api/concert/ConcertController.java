package com.my.api.concert;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/concerts")
@RestController
public class ConcertController {

    @GetMapping("/{concertId}/available-dates")
    public ResponseEntity<Void> getAvailableDates(@PathVariable("concertId") String concertId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{concertId}/{dateId}/remain-seats")
    public ResponseEntity<Void> getRemainSeats(@PathVariable("concertId") String concertId, @PathVariable("dateId") String dateId) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{concertId}/{dateId}/reserve")
    public ResponseEntity<Void> doReserve(@PathVariable("concertId") String concertId, @PathVariable("dateId") String dateId) {
        return ResponseEntity.ok().build();
    }
}
