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

        UserEntity userEntity = userJpaRepo.findByEmail(email)
            .orElseGet(() -> new UserEntity(email));

        if (userEntity.isWalletEmpty()) {
            PayEntity wallet = new PayEntity(userEntity);
            userEntity.addWallet(wallet);
        }

        userJpaRepo.save(userEntity);

        return new User(userEntity.getUserId().toString(), userEntity.getEmail());
    }
}
