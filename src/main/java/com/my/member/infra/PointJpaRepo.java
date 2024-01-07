package com.my.member.infra;

import com.my.member.domain.Member;
import com.my.member.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PointJpaRepo extends JpaRepository<Member, Long> {

	@Query("""
			select
			new com.my.member.domain.Point(m.point.point)
			  from Member m
			 where m.memberId = :memberId
			""")
	Optional<Point> findPoint(@Param("memberId") Long memberId);

}
