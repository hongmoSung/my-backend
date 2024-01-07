package com.my.member.domain;

import com.my.member.api.PointRechargeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PointService {

	private final PointRepo pointRepo;

	public Point getPoint(Long memberId) {
		return pointRepo.findPoint(memberId).orElseThrow(() -> new RuntimeException(memberId + "에 해당하는 사용자가 없습니다."));
	}

}
