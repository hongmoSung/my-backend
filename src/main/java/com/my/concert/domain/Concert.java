package com.my.concert.domain;

import com.my.concert.domain.seat.domain.Seat;
import com.my.concert.infra.mysql.ConcertEntity;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Concert {

    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private int capacity = 50;

    @Setter
    private List<Seat> seats = new ArrayList<>();

    @Builder
    public Concert(Long id, String name, LocalDate startDate, LocalDate endDate) {
        this.id = id;
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
