package com.my.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.my.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/tokens")
    public ResponseEntity<ResTokenDto> getToken(@RequestBody TokenReqDto tokenReqDto) throws JsonProcessingException {
        return ResponseEntity.ok(userService.getToken(tokenReqDto.toToken()));
    }
}
