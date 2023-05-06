package itsum.study.posts.dto;

import itsum.study.posts.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostsListRequestDto {
    private String title;
    private String contents;
    private String positionList ;
    private int personnel;
    private String techSkill;
    private String meetingWay ;
}