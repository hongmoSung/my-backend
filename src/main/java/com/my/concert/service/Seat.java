package com.my.concert.service;

import com.my.concert.repo.SeatEntity;
import com.my.concert.repo.ViewingDateEntity;

public class Seat {

    private int seatNumber;

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public SeatEntity toEntity(ViewingDateEntity viewingDateEntity) {
        return SeatEntity.builder()
                .seatNumber(seatNumber)
                .viewingDate(viewingDateEntity)
                .build();
    }
}
