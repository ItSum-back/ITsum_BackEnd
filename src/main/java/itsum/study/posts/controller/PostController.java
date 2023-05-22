package itsum.study.posts.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import itsum.study.auth.dto.AuthRequest;
import itsum.study.auth.dto.AuthResponse;
import itsum.study.auth.jwt.AuthToken;
import itsum.study.auth.jwt.JwtHeaderUtil;
import itsum.study.posts.common.SliceResult;
import itsum.study.posts.domain.Post;
import itsum.study.posts.dto.PostsCreateRequestDto;
import itsum.study.posts.dto.PostsListResponseDto;
import itsum.study.posts.dto.PostsResponseDto;
import itsum.study.posts.dto.PostsUpdateRequestDto;
import itsum.study.posts.service.PagingResponseService;
import itsum.study.posts.service.PostsService;
import itsum.study.utils.dto.ApiResponse;
import itsum.study.utils.dto.DataResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.awt.print.Book;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostsService postsService;
    private final PagingResponseService responseService;


    /**
     * Post 목록 생성 (무한 스크롤)
     * @return ResponseEntity<PostResponse>
     */
    @ApiOperation(value = "모집글 목록 생성", notes = "무한스크롤을 이용한 모집글 목록 생성")
    @GetMapping
    public ResponseEntity<SliceResult<PostsListResponseDto>> searchAllPosts(
            @ApiParam(value = "title", required = false)
            @RequestParam(value = "title",required = false) String title,
            @ApiParam(value = "contents", required = false)
            @RequestParam(value = "contents",required = false) String contents,
            @ApiParam(value = "positionList", required = false)
            @RequestParam(value = "positionList",required = false) String positionList,
            @ApiParam(value = "techSkill", required = false)
            @RequestParam(value = "techSkill",required = false) String techSkill,
            @ApiParam(value = "meetingWay", required = false)
            @RequestParam(value = "meetingWay",required = false) String meetingWay,
            Pageable pageable) {

        return new ResponseEntity<>(responseService.getSliceResult(
                postsService.findPostAllByCreatedAtDesc(title,contents,positionList,techSkill,meetingWay,pageable)), HttpStatus.OK);
    }

    /**
     * Post 생성
     * @return ID
     */
    @ApiOperation(value = "모집글 생성", notes = "모집글을 생성한다.")
    @PostMapping
    public DataResponseDto<Long> createPost(@RequestBody PostsCreateRequestDto requestDto) {
        return DataResponseDto.of(postsService.savePost(requestDto));
    }
    /**
     * Post 수정
     * @return ID
     */
    @ApiOperation(value = "모집글 수정", notes = "모집글의 ID 값을 통해 업데이트 한다.")
    @PutMapping("/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return  postsService.update(id, requestDto);
    }

    /**
     * Post 삭제
     * @return ID
     */
    @ApiOperation(value = "모집글 삭제", notes = "모집글의 ID 값을 통해 삭제")
    @DeleteMapping("/{id}")
    public Long deletePost(@PathVariable Long id) {
        return postsService.delete(id);
    }

    /**
     * Post 상세 조회
     * @return ResponseEntity<PostResponse>
     */
    @ApiOperation(value = "모집글 상세조회", notes = "모집글의 ID 값을 통해 상세 조회")
    @GetMapping("/{id}")
    public DataResponseDto<PostsResponseDto> GetPost (@PathVariable Long id) {
        return DataResponseDto.of(postsService.findById(id));
    }

}
