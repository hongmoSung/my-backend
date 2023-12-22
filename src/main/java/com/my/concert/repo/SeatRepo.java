package com.my.concert.repo;

import com.my.concert.service.Seat;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepo extends JpaRepository<SeatEntity, Long> {

    Optional<SeatEntity> findByViewingDateAndSeatNumber(ViewingDateEntity viewingDate, int seatNumber);

}
