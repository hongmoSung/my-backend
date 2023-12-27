package com.my.concert.domain.booking.domain;

import com.my.concert.infra.mysql.ConcertEntity;
import com.my.concert.domain.booking.infra.BookingEntity;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Booking {

    private LocalDate date;

    public Booking(LocalDate date) {
        this.date = date;
    }

}
