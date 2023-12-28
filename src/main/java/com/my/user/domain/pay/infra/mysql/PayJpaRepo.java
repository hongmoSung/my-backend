package com.my.user.domain.pay.infra.mysql;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayJpaRepo extends JpaRepository<PayEntity, Long> {

	Optional<PayEntity> findByUserUserId(UUID userUUid);

}
