package com.my.concert.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.my.concert.service.Concert;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class CreateConcertDto {

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public Concert toConcert() {
        return Concert.builder()
                .name(name)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
