package com.my.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberId;

	private String name;

	private Point point;

	public void recharge(BigInteger point) {
		this.point = new Point(this.point.getPoint().add(point));
	}

	public void pay(Point payPoint) {
		BigInteger nowPoint = this.point.getPoint();
		BigInteger orderPoint = payPoint.getPoint();
		if (nowPoint.compareTo(orderPoint) < 0) {
			throw new RuntimeException("잔액이 부족합니다.");
		}
		this.point = new Point(nowPoint.subtract(orderPoint));
	}

}
