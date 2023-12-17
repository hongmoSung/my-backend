package com.my.concert.repo;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewingDateRepo extends JpaRepository<ViewingDateEntity, Long> {

    Optional<ViewingDateEntity> findByConcertIdAndDate(Long concertId, LocalDate date);

}
