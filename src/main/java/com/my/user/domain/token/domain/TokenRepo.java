package com.my.user.domain.token.domain;

import com.my.user.domain.token.infra.mysql.TokenEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo {

    void saveToken(TokenEntity tokenEntity);
}
