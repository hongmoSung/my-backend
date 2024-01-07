package com.my.product.api;

import com.my.product.domain.Product;
import com.my.product.domain.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductController {

	private final ProductRepo productRepo;

	@GetMapping("/{productId}")
	public ResponseEntity<ProductInfoResponse> getProductInfo(@PathVariable("productId") Long productId) {
		Product product = productRepo.getProduct(productId);
		return ResponseEntity.ok(ProductInfoResponse.of(product));
	}

}
