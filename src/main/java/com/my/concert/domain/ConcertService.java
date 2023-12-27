package com.my.concert.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ConcertService {

    private final ConcertRepo concertRepo;

    @Transactional
    public void createConcert(Concert concert) {
        concertRepo.saveConcert(concert);
    }
}
