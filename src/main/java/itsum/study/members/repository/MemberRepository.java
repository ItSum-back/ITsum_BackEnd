package itsum.study.members.repository;


import itsum.study.members.domain.Members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Members, Long> {

    Members findMemberById(Long memberId);
}
