package com.my.concert.controller.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResRemainSeats {

    private String Date;
    private List<Integer> seatNumbers;

    @Builder
    public ResRemainSeats(String date, List<Integer> seatNumbers) {
        Date = date;
        this.seatNumbers = seatNumbers;
    }
}
