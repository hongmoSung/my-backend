package com.my.user.infra.mysql;

import com.my.user.infra.UserEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepo extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);
}
