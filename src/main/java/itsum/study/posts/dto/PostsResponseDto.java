package itsum.study.posts.dto;

import itsum.study.comment.domain.Comment;
import itsum.study.members.domain.Members;
import itsum.study.posts.domain.Post;
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
    private String meetingWays ;
    private LocalDateTime createdAt;//created
    private LocalDateTime modifiedAt;//modified
    private Members members;
    private List<Comment> comments;

    public PostsResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.view = entity.getView();
        this.positionList = entity.getPositionList();
        this.personnel = entity.getPersonnel();
        this.techSkill = entity.getTechSkill();
        this.meetingWays = entity.getMeetingWays();
        this.createdAt = entity.getCreatedAt();
        this.modifiedAt = entity.getModifiedAt();
        this.members = entity.getMembers();
        this.comments = entity.getComments();
    }
}
