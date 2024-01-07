package com.my.product.infra;

import com.my.product.domain.Product;
import com.my.product.domain.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductRepoImpl implements ProductRepo {

	private final ProductJpaRepo productJpaRepo;

	@Override
	public Product getProduct(Long productId) {
		return productJpaRepo.findById(productId)
			.orElseThrow(() -> new RuntimeException(productId + "에 해당하는 상품이 없습니다."));
	}

}
