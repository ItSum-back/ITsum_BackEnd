package itsum.study.members.domain;


import com.querydsl.jpa.impl.JPAQueryFactory;
import itsum.study.auth.enumerate.RoleType;
import itsum.study.members.enumerate.MemberProvider;
import itsum.study.posts.domain.Posts;
import itsum.study.utils.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Members extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickname;

    @Column
    private String email;


    @Column(nullable = false)
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberProvider memberProvider;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType roleType;

    @OneToMany(mappedBy = "members")
    private List<Posts> posts = new ArrayList<>();


    public void updateNickname(String name) {
        this.name = name;
    }


}