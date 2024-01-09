package com.my.member.domain;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@Access(AccessType.FIELD)
public class Point {

	private BigInteger point = BigInteger.ZERO;

	public Point(BigInteger point) {
		this.point = point;
	}

	public BigInteger getPoint() {
		return point;
	}

}
