package com.my.member.infra;

import com.my.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepo extends JpaRepository<Member, Long> {

}
