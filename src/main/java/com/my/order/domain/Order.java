package com.my.order.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	private Orderer orderer;

	private BigInteger totalPrice = BigInteger.ZERO;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderLine> orderLines;

	@Builder
	public Order(Orderer orderer, List<OrderLine> orderLine) {
		this.orderer = orderer;
		this.orderLines = orderLine;
		this.orderLines.forEach((o) -> o.addOrder(this));
		orderLine.forEach((o) -> totalPrice = totalPrice.add(o.getOrderPrice()));
	}

}
