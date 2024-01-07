package com.my.order.infra;

import com.my.order.domain.Order;
import com.my.order.domain.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderRepoImpl implements OrderRepo {

	private final OrderJpaRepo orderJpaRepo;

	@Override
	public Order save(Order order) {
		return orderJpaRepo.save(order);
	}

}
