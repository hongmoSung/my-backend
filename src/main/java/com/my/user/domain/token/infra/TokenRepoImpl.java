package com.my.user.domain.token.infra;

import com.my.user.domain.token.domain.TokenRepo;
import com.my.user.domain.token.infra.mysql.TokenEntity;
import com.my.user.domain.token.infra.mysql.TokenJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TokenRepoImpl implements TokenRepo {

	private final TokenJpaRepo tokenJpaRepo;

	@Override
	public void saveToken(TokenEntity tokenEntity) {
		tokenJpaRepo.save(tokenEntity);
	}

}
