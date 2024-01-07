package com.my.member.api;

import com.my.member.domain.Point;
import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;

public record PointResponse(BigInteger point) {

	@Builder
	public PointResponse {
	}

	public static PointResponse of(Point point) {
		return PointResponse.builder().point(point.getPoint()).build();
	}
}
