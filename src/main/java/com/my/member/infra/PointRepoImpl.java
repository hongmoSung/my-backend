package com.my.member.infra;

import com.my.member.domain.Point;
import com.my.member.domain.PointRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class PointRepoImpl implements PointRepo {

	private final PointJpaRepo pointJpaRepo;

	@Override
	public Optional<Point> findPoint(Long memberId) {
		return pointJpaRepo.findPoint(memberId);
	}

}
