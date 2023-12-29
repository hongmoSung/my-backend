package com.my.token.infra.mysql;

import com.my.token.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenJpaRepo extends JpaRepository<TokenEntity, Long> {

	@Query("select count(t.id) from token t where t.status = :status")
	int getWorkingTokenCount(@Param("status") Token.Status status);

	@Query("select t from token t where t.status = :status and t.tokenString = :tokenString")
	Optional<TokenEntity> findWorkTokenByTokenString(@Param("status") Token.Status status,
			@Param("tokenString") String tokenString);

	@Query("select t from token t where t.status = 'WAIT' order by t.createAt asc limit 1")
	Optional<TokenEntity> findFirstWaitingToken();

}
