package com.my.member.domain;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PointRepo {

	Optional<Point> findPoint(Long memberId);

}
