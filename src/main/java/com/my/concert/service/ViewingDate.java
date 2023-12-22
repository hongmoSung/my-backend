package com.my.concert.service;

import com.my.concert.repo.ConcertEntity;
import com.my.concert.repo.ViewingDateEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ViewingDate {

    private LocalDate date;
    private int capacity = 50;

    public ViewingDate(LocalDate date) {
        this.date = date;
    }

    public ViewingDateEntity toEntity(ConcertEntity concertEntity) {
        return ViewingDateEntity.builder()
                .date(date)
                .capacity(capacity)
                .concert(concertEntity)
                .build();
    }
}
