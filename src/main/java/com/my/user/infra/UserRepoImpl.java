package com.my.user.infra;

import com.my.user.domain.User;
import com.my.user.domain.UserRepo;
import com.my.user.domain.pay.infra.mysql.PayEntity;
import com.my.user.infra.mysql.UserJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class UserRepoImpl implements UserRepo {

	private final UserJpaRepo userJpaRepo;

	@Transactional
	@Override
	public User getOrCreateUserByEmail(String email) {

		UserEntity userEntity = userJpaRepo.findByEmail(email).orElseGet(() -> new UserEntity(email));

		if (userEntity.isPayEmpty()) {
			PayEntity payEntity = new PayEntity(userEntity);
			userEntity.addPay(payEntity);
		}

		userJpaRepo.save(userEntity);

		return new User(userEntity.getUserId().toString(), userEntity.getEmail());
	}

}
