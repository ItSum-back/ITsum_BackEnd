package itsum.study.comment.dto;

import itsum.study.comment.domain.Comment;
import itsum.study.posts.domain.Post;
import itsum.study.posts.dto.PostsListResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentListResponseDto {
    private Long id;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String creatorName;
    private Comment parent;

    public CommentListResponseDto(Comment c) {
        this.id = c.getId();
        this.contents = c.getContents();
        this.createdAt = c.getModifiedAt();
        this.creatorName = c.getCreatorName();
        this.parent = c.getParent();
    }


    public static ArrayList<CommentListResponseDto> toCommentListResponse(List<Comment> fetch) {

        ArrayList<CommentListResponseDto> responseDtoList = new ArrayList<>();

        for(Comment c : fetch){
            responseDtoList.add(new CommentListResponseDto(c));
        }
        return responseDtoList;
    }
}
