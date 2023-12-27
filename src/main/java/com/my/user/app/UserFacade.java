package com.my.user.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.my.user.api.dto.ResTokenDto;
import com.my.user.domain.User;
import com.my.user.domain.UserService;
import com.my.user.domain.token.domain.Token;
import com.my.user.domain.token.domain.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserFacade {

    private final UserService userService;
    private final TokenService tokenService;

    public ResTokenDto getTokenString(String email) throws JsonProcessingException {

        User user = userService.getUser(email);
        Token token = tokenService.getToken(user);

        tokenService.saveToken(token);

        return new ResTokenDto(token.getTokenString());
    }
}
