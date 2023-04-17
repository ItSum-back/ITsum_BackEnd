package itsum.study.members.domain;


import com.querydsl.jpa.impl.JPAQueryFactory;
import itsum.study.auth.enumerate.RoleType;
import itsum.study.members.enumerate.MemberProvider;
import itsum.study.utils.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Members extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String email;

    @Column
    private String gender;

    @Column
    private Integer breadTestResult;

    @Column(nullable = false)
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberProvider memberProvider;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType roleType;

    @Column
    private String profileImagePath;

    public void updateName(String name) {
        this.name = name;
    }

    public void updateProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }
}