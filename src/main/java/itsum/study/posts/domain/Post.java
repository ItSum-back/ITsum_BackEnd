package itsum.study.posts.domain;
import itsum.study.utils.domain.BaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE post SET deleted = true WHERE post_id = ?")
@Where(clause = "deleted = false") //
@Table(name="post")
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
    private String members;
    @Column
    private String category;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="project_start_time")
    private LocalDateTime projectStartTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="project_end_time")
    private LocalDateTime projectEndTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column
    private LocalDateTime deadline;
    @Column
    private String contact;

    public void update(Post entity) {
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.positionList = entity.getPositionList();
        this.personnel = entity.getPersonnel();
        this.techSkill = entity.getTechSkill();
        this.meetingWay = entity.getMeetingWay();
        this.members = entity.getMembers();
        this.category = entity.getCategory();
        this.projectStartTime = entity.getProjectStartTime();
        this.projectEndTime = entity.getProjectEndTime();
        this.deadline = entity.getDeadline();
        this.contact = entity.getContact();
    }

    public Post(long id, String title){
        this.id= id;
        this.title = title;
    }
}
