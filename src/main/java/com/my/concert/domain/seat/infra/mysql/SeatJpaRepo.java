package com.my.concert.domain.seat.infra.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatJpaRepo extends JpaRepository<SeatEntity, Long> {
}
