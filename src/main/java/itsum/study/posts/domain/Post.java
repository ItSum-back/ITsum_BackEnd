package itsum.study.posts.domain;

import itsum.study.comment.domain.Comment;
import itsum.study.members.domain.Members;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
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
@SQLDelete(sql = "UPDATE post SET deletedAt = true WHERE id = ?")
public class Post {

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
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime createdAt;//created

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime modifiedAt;//modified

    private boolean deleted = Boolean.FALSE; // 삭제 여부 기본값 false

    @ManyToOne
    @JoinColumn(name="members_id")
    private Members members;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<Comment>();




}
