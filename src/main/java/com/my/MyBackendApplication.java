package com.my;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBackendApplication.class, args);
	}

	@Bean
	JPAQueryFactory queryFactory(EntityManager em) {
		return new JPAQueryFactory(em);
	}

}
