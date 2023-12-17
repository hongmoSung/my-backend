package com.my.user.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class ResTokenDto {

    private String token;
    private String type = "Bearer";

    @Builder
    public ResTokenDto(String token) {
        this.token = token;
    }
}
