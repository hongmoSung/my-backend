package com.my.token.domain;

import com.my.token.infra.mysql.TokenEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepo {

	void saveToken(TokenEntity tokenEntity);

	int getWorkingTokenCount();

	void changeToDone(Token token);

	Optional<TokenEntity> findFirstWaitingToken();

}
