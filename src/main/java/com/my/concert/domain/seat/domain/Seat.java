package com.my.concert.domain.seat.domain;

import com.my.concert.domain.seat.infra.mysql.SeatEntity;

import com.my.concert.infra.mysql.ConcertEntity;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Seat {

    private Long id;
    private int no;
    private int price = 5000;
    private LocalDate date;

    public Seat(int no, LocalDate date) {
        this.no = no;
        this.date = date;
    }

    @Builder
    public Seat(Long id, int no, LocalDate date) {
        this(no, date);
        this.id = id;
        this.date = date;
    }

    public SeatEntity toEntity(ConcertEntity concert) {
        return SeatEntity.builder()
                .no(no)
                .date(date)
                .concert(concert)
                .build();
    }
}
