package com.my.concert.controller.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResConcertDto {

    private String name;

    private List<String> availableDates;

    @Builder
    public ResConcertDto(String name, List<String> availableDates) {
        this.name = name;
        this.availableDates = availableDates;
    }
}
