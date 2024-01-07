package com.my.member.api;

import com.my.member.domain.MemberService;
import com.my.member.domain.Point;
import com.my.member.domain.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/points")
@RestController
public class PointController {

	private final PointService pointService;

	private final MemberService memberService;

	@GetMapping("/{memberId}")
	public ResponseEntity<PointResponse> getPoint(@PathVariable("memberId") Long memberId) {
		Point point = pointService.getPoint(memberId);
		return ResponseEntity.ok(PointResponse.of(point));
	}

	@PutMapping
	public ResponseEntity<Void> pointRecharge(@RequestBody PointRechargeRequest request) {
		memberService.recharge(request);
		return ResponseEntity.noContent().build();
	}

}
