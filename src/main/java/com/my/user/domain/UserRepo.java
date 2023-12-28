package com.my.user.domain;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo {

	User getOrCreateUserByEmail(String email);

}
