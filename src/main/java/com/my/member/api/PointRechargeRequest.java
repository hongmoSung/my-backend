package com.my.member.api;

import java.math.BigInteger;

public record PointRechargeRequest(Long memberId, BigInteger point) {
}
