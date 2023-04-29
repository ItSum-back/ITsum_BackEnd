package itsum.study.posts.dto;

import itsum.study.comment.domain.Comment;
import itsum.study.members.domain.Members;
import itsum.study.posts.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String title;
    private String contents;
    private String positionList ;
    private int personnel;
    private String techSkill;
    private String meetingWays ;
    private Members members;
    private List<Comment> comments;

    //resquest dto 로 받은 Posts 객체를 entity 화하여 저장하는 용도
    public Post toEntity() {
        return Post.builder().title(title).contents(contents).positionList(positionList).personnel(personnel)
                .techSkill(techSkill).meetingWays(meetingWays).members(members).comments(comments).build();
    }
}
