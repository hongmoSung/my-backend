package com.my.member.infra;

import com.my.member.domain.Member;
import com.my.member.domain.MemberRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class MemberRepoImpl implements MemberRepo {

	private final MemberJpaRepo memberJpaRepo;

	@Override
	public Optional<Member> findMember(Long memberId) {
		return memberJpaRepo.findById(memberId);
	}

	@Override
	public Member getMember(Long memberId) {
		return memberJpaRepo.findById(memberId).orElseThrow(() -> new RuntimeException(memberId + "에 해당하는 사용자가 없습니다."));
	}

}
