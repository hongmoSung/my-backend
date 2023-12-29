package com.my.user.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepo userRepo;

	public User getUser(String email) {
		return userRepo.getOrCreateUserByEmail(email);
	}

}
