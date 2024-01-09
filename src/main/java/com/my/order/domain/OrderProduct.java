package com.my.order.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class OrderProduct {

	private Long productId;

	private BigInteger productPrice;

	public OrderProduct(Long productId) {
		this.productId = productId;
	}

}
