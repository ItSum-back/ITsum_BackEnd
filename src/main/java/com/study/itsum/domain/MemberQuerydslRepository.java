package com.study.itsum.domain;

import com.nimbusds.openid.connect.sdk.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.transaction.Transactional;

@Repository
@RequiredArgsConstructor
public class MemberQuerydslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Transactional(readOnly = true)
    public Members findBySocialId(String socialId) {
        return jpaQueryFactory
                .selectFrom(members)
                .where(members.socialId.eq(socialId))
                .fetchOne();
    }

    public UserInfoResponse findByMemberId(Long memberId) {
        return jpaQueryFactory
                .select(Projections.fields(UserInfoResponse.class,
                        members.profileImagePath.as("profileImage"),
                        members.name.as("nickName"),
                        members.email))
                .from(members)
                .where(members.id.eq(memberId))
                .fetchOne();
    }
}