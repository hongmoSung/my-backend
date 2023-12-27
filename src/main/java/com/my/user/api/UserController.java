package com.my.user.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.my.user.api.dto.ReqTokenDto;
import com.my.user.api.dto.ResTokenDto;
import com.my.user.app.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    private final UserFacade userFacade;

    @PostMapping("/tokens")
    public ResponseEntity<ResTokenDto> getToken(@RequestBody ReqTokenDto reqTokenDto) throws JsonProcessingException {
        return ResponseEntity.ok(userFacade.getTokenString(reqTokenDto.getEmail()));
    }
}
