package com.my.member.domain;

import com.my.member.api.PointRechargeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepo memberRepo;

	@Transactional
	public void recharge(PointRechargeRequest request) {
		Member member = memberRepo.getMember(request.memberId());
		member.recharge(request.point());
	}

	public void pay(Long memberId, BigInteger totalPrice) {
		Member member = memberRepo.getMember(memberId);
		member.pay(new Point(totalPrice));
	}

}
