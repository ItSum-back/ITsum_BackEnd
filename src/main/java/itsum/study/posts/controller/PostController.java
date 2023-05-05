package itsum.study.posts.controller;

import io.swagger.annotations.ApiOperation;
import itsum.study.auth.dto.AuthRequest;
import itsum.study.auth.dto.AuthResponse;
import itsum.study.auth.jwt.AuthToken;
import itsum.study.auth.jwt.JwtHeaderUtil;
import itsum.study.posts.dto.PostsCreateRequestDto;
import itsum.study.posts.dto.PostsResponseDto;
import itsum.study.posts.dto.PostsUpdateRequestDto;
import itsum.study.posts.service.PostsService;
import itsum.study.utils.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostsService postsService;
    /**
     * Post 생성
     * @return post_id
     */
    @ApiOperation(value = "모집글 생성", notes = "모집글을 생성한다.")
    @PostMapping
    public Long createPost(@RequestBody PostsCreateRequestDto requestDto) {
        return  postsService.savePost(requestDto);
    }
    /**
     * Post 수정
     * @return ResponseEntity<PostResponse>
     */
    @ApiOperation(value = "모집글 수정", notes = "모집글의 ID 값을 통해 업데이트 한다.")
    @PutMapping("/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return  postsService.update(id, requestDto);
    }

    /**
     * Post 삭제
     * @return ResponseEntity<PostResponse>
     */
    @ApiOperation(value = "모집글 삭제", notes = "모집글의 ID 값을 통해 삭제")
    @DeleteMapping("/{id}")
    public Long deletePost(@PathVariable Long id) {
        return postsService.delete(id);
    }

    /**
     * Post 삭제
     * @return ResponseEntity<PostResponse>
     */
    @ApiOperation(value = "모집글 상세조회", notes = "모집글의 ID 값을 통해 상세 조회")
    @GetMapping("/{id}")
    public PostsResponseDto GetPost (@PathVariable Long id) {
        return postsService.findById(id);
    }


}
