package com.my.order.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Orderer {

	private Long memberId;

	public Orderer(Long memberId) {
		this.memberId = memberId;
	}

}
