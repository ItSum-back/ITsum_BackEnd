package itsum.study.members.service;

import itsum.study.members.repository.MemberQuerydslRepository;
import itsum.study.members.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberQuerydslRepository memberRepository;

    public MemberService(MemberQuerydslRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean updateNickName(Long memberId, String nickName) {
        return memberRepository.updateNickNameByMemberId(memberId, nickName);
    }
}
