package itsum.study.members.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import itsum.study.members.domain.Members;
import itsum.study.members.dto.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static itsum.study.members.domain.QMembers.members;

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
