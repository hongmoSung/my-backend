package com.my.order.api;

import com.my.order.domain.OrderLine;
import com.my.order.domain.OrderProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderRequest {

	private Long memberId;

	private List<ProductRequest> products;

	@Getter
	@NoArgsConstructor
	public static class ProductRequest {

		private Long productId;

		private BigInteger price;

		private BigInteger quantity;

		public OrderLine toOrderLine() {
			return OrderLine.builder()
				.orderProduct(new OrderProduct(this.productId))
				.price(this.price)
				.quantity(this.quantity)
				.build();
		}

	}

}
