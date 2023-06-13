package itsum.study.comment.dto;

import itsum.study.comment.domain.Comment;

public class CommentsCreateRequestDto {
    private String contents;
    private String creatorName; // 작성자
    private Long commentId;
    private Long post_id;


    public Comment toEntity(Long post_id){
        return Comment.builder().contents(contents).creatorName(creatorName).post_id(post_id).build();
    }
}