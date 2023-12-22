package com.my.pay.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepo extends JpaRepository<ReserveEntity, Long> {

}
