package com.my.order.application;

import com.my.member.domain.MemberService;
import com.my.order.api.OrderRequest;
import com.my.order.domain.Order;
import com.my.order.domain.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderFacade {

	private final OrderService orderService;

	private final MemberService memberService;

	@Transactional
	public void order(OrderRequest request) {
		Order order = orderService.createOrder(request);
		memberService.pay(request.getMemberId(), order.getTotalPrice());
	}

}
