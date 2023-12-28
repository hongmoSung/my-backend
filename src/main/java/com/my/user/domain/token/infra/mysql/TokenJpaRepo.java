package com.my.user.domain.token.infra.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenJpaRepo extends JpaRepository<TokenEntity, Long> {

}
