package com.my.order.infra;

import com.my.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepo extends JpaRepository<Order, Long> {

}
