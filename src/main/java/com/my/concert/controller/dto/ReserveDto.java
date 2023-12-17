package com.my.concert.controller.dto;

import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ReserveDto {

    private UUID userId;
    private Long concertId;
    private String date;
    private int seatNumber;

}
