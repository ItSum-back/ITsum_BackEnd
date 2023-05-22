package itsum.study.comment.controller;


import io.swagger.annotations.ApiOperation;
import itsum.study.comment.dto.CommentsCreateRequestDto;
import itsum.study.comment.service.CommentService;
import itsum.study.utils.dto.DataResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentsService;

    /**
     * Comment 등록
     * @return ID
     */
    @ApiOperation(value = "댓글 생성", notes = "게시글 id를 받아 댓글을 등록합니다.")
    @PostMapping("/posts/{postId}/comments")
    public DataResponseDto<Long> registerComment(@PathVariable Long postId,
                                                 @RequestBody CommentsCreateRequestDto commentsCreateRequestDto){
        return DataResponseDto.of(commentsService.saveComments(postId,commentsCreateRequestDto));
    }

    /**
     * Commnet 수정
     * @return ID
     */
    @ApiOperation(value = "댓글 수정", notes = "댓글 ID 값을 통해 업데이트한다.")
    @PutMapping("posts/comments/{commentId}")
    public DataResponseDto<Long> updateComment(@PathVariable Long commentId ,@RequestBody Map<String,String> contentsMap){

        return DataResponseDto.of(commentsService.updateComments(commentId,contentsMap.get("contents")));
    }
    
    // /posts/{postId}/comments/{commentId} 삭제

    // /posts/{postId}/comments/{commetId}/reply 대댓글 등록



}
