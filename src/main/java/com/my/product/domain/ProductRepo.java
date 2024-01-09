package com.my.product.domain;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo {

	Product getProduct(Long productId);

}
