package com.my.concert.service;

import com.my.concert.repo.ConcertEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.Period;

public class Concert {

    private String name;

    @Getter
    private LocalDate startDate;
    private LocalDate endDate;

    @Builder
    public Concert(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ConcertEntity toEntity() {
        return ConcertEntity.builder()
                .name(name)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }

    public int getConcertPeriodDays() {
        return Period.between(startDate, endDate).getDays();
    }
}
