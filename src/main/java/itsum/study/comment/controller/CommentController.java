package itsum.study.comment.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public class CommentController {

    @ApiOperation(value = "댓글 등록", notes = "댓글을 등록한다.")
    @PostMapping("/posts/{postId}/comments")
    public



}
