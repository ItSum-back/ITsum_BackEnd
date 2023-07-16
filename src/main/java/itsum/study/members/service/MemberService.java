package itsum.study.members.service;

import itsum.study.members.repository.MemberQuerydslRepository;
import itsum.study.members.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberQuerydslRepository memberRepository;

    public MemberService(MemberQuerydslRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public boolean updateNickName(String memberId, String nickName) {
        return memberRepository.updateNickNameByMemberId(memberId, nickName);
    }
}
