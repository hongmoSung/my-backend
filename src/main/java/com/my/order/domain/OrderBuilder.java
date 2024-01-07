package com.my.order.domain;

import com.my.order.api.OrderRequest;

import java.util.List;

public class OrderBuilder {

	public static Order createOrder(OrderRequest request) {
		List<OrderLine> orderLines = request.getProducts()
			.stream()
			.map(OrderRequest.ProductRequest::toOrderLine)
			.toList();

		return Order.builder().orderer(new Orderer(request.getMemberId())).orderLine(orderLines).build();

	}

}
