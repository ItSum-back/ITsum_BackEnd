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
    @Column
    private Long id;

    @Column
    private String title;
    @Column
    private String contents;
    @Column
    private int view;
    @Column(name="position_list")
    private String positionList ;
    @Column
    private int personnel;
    @Column(name="tech_skill")
    private String techSkill;
    @Column(name="meeting_way")
    private String meetingWay ;
    @Column
    private boolean deleted = Boolean.FALSE; // 삭제 여부 기본값 false
    @Column
    private String members;

    public void update(String title, String contents, String positionList, int personnel, String techSkill, String meetingWay, String members) {
        this.title = title;
        this.contents = contents;
        this.positionList = positionList;
        this.personnel = personnel;
        this.techSkill = techSkill;
        this.meetingWay = meetingWay;
        this.members = members;
    }

    public Post(long id, String title){
        this.id= id;
        this.title = title;
    }

    public void delete() {
       this.deleted = true;
    }
}
