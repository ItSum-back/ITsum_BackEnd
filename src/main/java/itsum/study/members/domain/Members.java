package itsum.study.members.domain;



import itsum.study.auth.enumerate.RoleType;
import itsum.study.comment.domain.Comment;
import itsum.study.members.enumerate.MemberProvider;
import itsum.study.posts.domain.Post;
import itsum.study.utils.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Members extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="members_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String nickname;

    @Column
    private String email;


    @OneToMany(mappedBy = "members")
    private List<Post> posts = new ArrayList<Post>();

    @OneToMany(mappedBy = "members")
    private List<Comment> comments = new ArrayList<Comment>();



    @Column(nullable = false)
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberProvider memberProvider;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType roleType;




    public void updateNickname(String name) {
        this.name = name;
    }


}