package com.my.user.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo {

    User getOrCreateUserByEmail(String email);
}
