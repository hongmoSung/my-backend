package com.my.concert.domain.booking.infra.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingJpaRepo extends JpaRepository<BookingEntity, Long> {
}
