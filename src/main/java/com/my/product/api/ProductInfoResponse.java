package com.my.product.api;

import com.my.product.domain.Product;
import lombok.Builder;

import java.math.BigInteger;

public record ProductInfoResponse(Long id, String name, BigInteger price, BigInteger quantity) {
	@Builder
	public ProductInfoResponse {
	}

	public static ProductInfoResponse of(Product product) {
		return ProductInfoResponse.builder()
			.id(product.getProductId())
			.name(product.getName())
			.price(product.getPrice())
			.quantity(product.getQuantity())
			.build();
	}

}
