package com.my.pay.repo;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepo extends JpaRepository<WalletEntity, Long> {

    Optional<WalletEntity> findByUserUserId(UUID userId);

}
