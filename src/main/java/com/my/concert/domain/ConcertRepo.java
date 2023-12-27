package com.my.concert.domain;

import org.springframework.stereotype.Repository;

@Repository
public interface ConcertRepo {

    Concert getConcert(Long id);

    void saveConcert(Concert concert);
}
