package com.my.concert.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertRepo extends JpaRepository<ConcertEntity, Long> {
}
