package com.my.user.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.my.user.api.dto.ResTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepo userRepo;

    public User getUser(String email) {
        return userRepo.getOrCreateUserByEmail(email);
    }
}
