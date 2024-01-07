package com.my.member.domain;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepo {

	Optional<Member> findMember(Long memberId);

	Member getMember(Long memberId);

}
