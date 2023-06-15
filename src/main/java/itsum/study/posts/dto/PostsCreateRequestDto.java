package itsum.study.posts.dto;

import itsum.study.members.domain.Members;
import itsum.study.posts.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostsCreateRequestDto {
    private String socialId;
    private String title;
    private String contents;
    private String positionList ;
    private int personnel;
    private String techSkill;
    private String meetingWay ;
    private String members;
    private String category;
    private LocalDateTime projectStartTime;
    private LocalDateTime projectEndTime;
    private LocalDateTime deadline;
    private String contact;

    //resquest dto 로 받은 Posts 객체를 entity 화하여 저장하는 용도
    public Post toEntity() {
        return Post.builder().title(title).contents(contents).positionList(positionList).personnel(personnel)
                .techSkill(techSkill).meetingWay(meetingWay).members(members).category(category).projectStartTime(projectStartTime).projectEndTime(projectEndTime).deadline(deadline)
                .contact(contact).socialId(socialId).build();
    }
}
