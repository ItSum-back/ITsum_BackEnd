package itsum.study.posts.domain;

import itsum.study.comment.domain.Comment;
import itsum.study.members.domain.Members;
import itsum.study.utils.domain.BaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE post SET deleted = true WHERE post_id = ?")
@Where(clause = "deleted = false") //
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="post_id")
    private Long id;
    private String title;
    private String contents;
    private int view;
    private String positionList ;
    private int personnel;
    private String techSkill;
    private String meetingWays ;

    private boolean deleted = Boolean.FALSE; // 삭제 여부 기본값 false

    @ManyToOne
    @JoinColumn(name="members_id")
    private Members members;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<Comment>();

    public void update(String title, String contents, String positionList, int personnel, String techSkill, String meetingWays, Members members) {
        this.title = title;
        this.contents = contents;
        this.positionList = positionList;
        this.personnel = personnel;
        this.techSkill = techSkill;
        this.meetingWays = meetingWays;
        this.members = members;
    }

    public void delete() {
        this.deleted = true;
    }
}
