package itsum.study.comment.dto;

import itsum.study.comment.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentsCreateRequestDto {
    private String contents;
    private String creatorName; // 작성자
    private Long post_id;
    private String socialId;


    public Comment toEntity(Long post_id){
        return Comment.builder().socialId(socialId).contents(contents).creatorName(creatorName).post_id(post_id).build();
    }
}