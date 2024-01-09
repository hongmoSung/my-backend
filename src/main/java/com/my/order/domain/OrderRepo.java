package com.my.order.domain;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo {

	Order save(Order order);

}
