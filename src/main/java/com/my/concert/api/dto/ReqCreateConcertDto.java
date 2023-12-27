package com.my.concert.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.my.concert.domain.Concert;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class ReqCreateConcertDto {

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
