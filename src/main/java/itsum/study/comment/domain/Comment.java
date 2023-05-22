package itsum.study.comment.domain;

import itsum.study.members.domain.Members;
import itsum.study.posts.domain.Post;
import itsum.study.utils.domain.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column
    private String contents;
    @Column
    private String creatorName;

    @Column
    private boolean deleted = Boolean.FALSE; // 삭제 여부 기본값 false
    
    //post
    @Column(name="post_id")
    private Long post_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    private Comment parent;


    @OneToMany(mappedBy = "parent", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Comment> children = new ArrayList<>();
    // 부모 댓글 수정
    public void updateParent(Comment parent){
        this.parent = parent;
    }
}
