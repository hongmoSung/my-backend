package com.my.order.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderLine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderLineId;

	@Embedded
	private OrderProduct orderProduct;

	private BigInteger price = BigInteger.ZERO;

	private BigInteger quantity = BigInteger.ZERO;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_ORDERLINE_ORDER_ORDER_ID"))
	private Order order;

	@Builder
	public OrderLine(OrderProduct orderProduct, BigInteger price, BigInteger quantity) {
		this.orderProduct = orderProduct;
		this.price = price;
		this.quantity = quantity;
	}

	public void addOrder(Order order) {
		this.order = order;
	}

	public BigInteger getOrderPrice() {
		return this.price.multiply(this.quantity);
	}

}
