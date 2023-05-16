package itsum.study.posts.dto;

import itsum.study.comment.domain.Comment;
import itsum.study.comment.dto.CommentResponseDto;
import itsum.study.members.domain.Members;
import itsum.study.posts.domain.Post;
import itsum.study.utils.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostsResponseDto {
    private Long id;
    private String title;
    private String contents;
    private int view;
    private String positionList ;
    private int personnel;
    private String techSkill;
    private String meetingWay ;
    private String members;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String category;
    private LocalDateTime projectStartTime;
    private LocalDateTime projectEndTime;
    private LocalDateTime deadline;
    private String contact;

    public PostsResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.view = entity.getView();
        this.positionList = entity.getPositionList();
        this.personnel = entity.getPersonnel();
        this.techSkill = entity.getTechSkill();
        this.meetingWay = entity.getMeetingWay();
        this.members = entity.getMembers();
        this.createdAt = entity.getCreatedAt();
        this.modifiedAt = entity.getModifiedAt();
        this.category = entity.getCategory();
        this.projectStartTime = entity.getProjectStartTime();
        this.projectEndTime = entity.getProjectEndTime();
        this.contact = entity.getContact();
    }


}
