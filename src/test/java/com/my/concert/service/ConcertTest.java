package com.my.concert.service;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ConcertTest {

    @Test
    void createConcert() {
        LocalDate now = LocalDate.now();
        LocalDate nowPlus10 = now.plusDays(10);
        Concert concert = Concert.builder()
                .name("name")
                .startDate(now)
                .endDate(nowPlus10)
                .build();

        int days = Period.between(now, nowPlus10).getDays();

        List<ViewingDate> viewingDateList = new ArrayList<>();

        for (var i = 0; i < days; i++) {
            ViewingDate viewingDate = new ViewingDate(now.plusDays(i));
            List<Seat> seats = new ArrayList<>();

            for (var j = 0; j < viewingDate.getCapacity(); j++) {
                Seat seat = new Seat(j + 1);
                seats.add(seat);
            }
            assertThat(seats.size()).isEqualTo(viewingDate.getCapacity());
            viewingDateList.add(viewingDate);
        }

        assertThat(viewingDateList.size()).isEqualTo(days);

    }

}