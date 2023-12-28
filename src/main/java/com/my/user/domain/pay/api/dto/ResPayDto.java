package com.my.user.domain.pay.api.dto;

import java.math.BigInteger;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ResPayDto {

	private String email = "";

	private BigInteger money = BigInteger.ZERO;

	@Builder
	public ResPayDto(String email, BigInteger money) {
		this.email = email;
		this.money = money;
	}

}
