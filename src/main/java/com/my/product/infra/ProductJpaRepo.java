package com.my.product.infra;

import com.my.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepo extends JpaRepository<Product, Long> {

}
