package com.my.order.domain;

import com.my.order.api.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {

	private final OrderRepo orderRepo;

	public Order createOrder(OrderRequest request) {
		Order order = OrderBuilder.createOrder(request);
		return orderRepo.save(order);
	}

}
