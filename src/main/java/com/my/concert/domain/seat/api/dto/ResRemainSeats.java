package com.my.concert.domain.seat.api.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResRemainSeats {

    private String date;
    private int count;

    @Builder
    public ResRemainSeats(LocalDate date, int count) {
        this.date = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.count = count;
    }
}
