package com.my.concert.infra.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertJpaRepo extends JpaRepository<ConcertEntity, Long> {
}
