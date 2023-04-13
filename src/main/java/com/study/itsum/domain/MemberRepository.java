package com.study.itsum.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Members,Long> {

    Members findMemberById(Long memberId);
}
