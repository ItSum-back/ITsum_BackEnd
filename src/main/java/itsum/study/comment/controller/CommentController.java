package itsum.study.comment.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.Response;
import itsum.study.comment.dto.CommentListResponseDto;
import itsum.study.comment.dto.CommentsCreateRequestDto;
import itsum.study.comment.service.CommentService;
import itsum.study.posts.common.SliceResult;
import itsum.study.posts.dto.PostsListResponseDto;
import itsum.study.posts.service.PagingResponseService;
import itsum.study.utils.dto.DataResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentsService;
    private final PagingResponseService responseService;

    /**
     * Comment 등록
     * @return ID
     */
    @ApiOperation(value = "댓글 목록 조회", notes = "게시글에 해당하는 댓글 리스트를 조회 합니다.")
    @GetMapping("/posts/{postId}/comments")
    public DataResponseDto<SliceResult<CommentListResponseDto>> ListComment(@PathVariable("postId") String id,
            Pageable pageable) {
        return DataResponseDto.of(responseService.getSliceResult(
                commentsService.findAllCommentsOrderByCreatedAtDesc(Long.valueOf(id), pageable)));
    }

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
    @ApiOperation(value = "댓글 수정", notes = "댓글 ID 값을 통해 업데이트")
    @PutMapping("posts/comments/{commentId}")
    public DataResponseDto<Long> updateComment(@PathVariable Long commentId ,@RequestBody Map<String,String> contentsMap){

        return DataResponseDto.of(commentsService.updateComments(commentId,contentsMap.get("contents")));
    }

    // /posts/{postId}/comments/{commentId} 삭제

    /**
     * 댓글 삭제
     * @return ID
     */
    @ApiOperation(value = "댓글 삭제", notes = "댓글의 ID 값을 통해 삭제")
    @DeleteMapping("/posts/comments/{commentId}")
    public Long deletePost(@PathVariable Long commentId) {
        return commentsService.delete(commentId);
    }

    // /posts/{postId}/comments/{commetId}/reply 대댓글 등록



}
