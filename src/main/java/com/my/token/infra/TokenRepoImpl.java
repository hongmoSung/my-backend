package com.my.token.infra;

import com.my.token.domain.Token;
import com.my.token.infra.mysql.TokenEntity;
import com.my.token.infra.mysql.TokenJpaRepo;
import com.my.token.domain.TokenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class TokenRepoImpl implements TokenRepo {

	private final TokenJpaRepo tokenJpaRepo;

	@Override
	public void saveToken(TokenEntity tokenEntity) {
		tokenJpaRepo.save(tokenEntity);
	}

	@Override
	public int getWorkingTokenCount() {
		return tokenJpaRepo.getWorkingTokenCount(Token.Status.WORK);
	}

	@Override
	public void changeToDone(Token token) {
		String tokenString = token.getTokenString();
		TokenEntity tokenEntity = tokenJpaRepo.findWorkTokenByTokenString(Token.Status.WORK, tokenString)
			.orElseThrow(() -> new RuntimeException("유효하지 않은 토큰 문자열 입니다."));
		tokenEntity.done();
	}

	@Override
	public Optional<TokenEntity> findFirstWaitingToken() {
		return tokenJpaRepo.findFirstWaitingToken();
	}

}
