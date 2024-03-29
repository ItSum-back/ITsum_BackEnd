package itsum.study.members.repository;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import itsum.study.members.domain.Members;
import itsum.study.members.dto.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

import static itsum.study.members.domain.QMembers.members;

@Repository
@RequiredArgsConstructor
public class MemberQuerydslRepository {
    private static final int UPDATE_FAIL = 0;
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
                        members.name.as("nickName"),
                        members.email))
                .from(members)
                .where(members.id.eq(memberId))
                .fetchOne();
    }

    public boolean updateNickNameByMemberId(String memberId, String newNickName) {
        long update = jpaQueryFactory
                .update(members)
                .set(members.name, newNickName)
                .where(members.socialId.eq(memberId))
                .execute();

        return update == UPDATE_FAIL;
    }
}
