package itsum.study.posts.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import itsum.study.posts.common.SliceResult;
import itsum.study.posts.dto.PostsCreateRequestDto;
import itsum.study.posts.dto.PostsListResponseDto;
import itsum.study.posts.dto.PostsResponseDto;
import itsum.study.posts.dto.PostsUpdateRequestDto;
import itsum.study.posts.service.PagingResponseService;
import itsum.study.posts.service.PostsService;
import itsum.study.utils.dto.DataResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostsService postsService;
    private final PagingResponseService responseService;

    /**
     * Post 목록 생성 (무한 스크롤)
     *
     * @return ResponseEntity<PostResponse>
     */
    @ApiOperation(value = "모집글 목록 조회", notes = "무한스크롤을 이용한 모집글 목록 생성")
    @GetMapping
    public SliceResult<PostsListResponseDto> searchAllPosts(
            @ApiParam(value = "title", required = false)
            @RequestParam(value = "title", required = false) String title,
            @ApiParam(value = "contents", required = false)
            @RequestParam(value = "contents", required = false) String contents,
            @ApiParam(value = "positionList", required = false)
            @RequestParam(value = "positionList", required = false) String positionList,
            @ApiParam(value = "techSkill", required = false)
            @RequestParam(value = "techSkill", required = false) String techSkill,
            @ApiParam(value = "meetingWay", required = false)
            @RequestParam(value = "meetingWay", required = false) String meetingWay,
            @ApiParam(value = "memberId", required = false)
            @RequestParam(value = "memberId", required = false) String memberId,
            Pageable pageable) {

        return responseService.getSliceResult(
                postsService.findPostAllByCreatedAtDesc(title, contents, positionList, techSkill, meetingWay, memberId, pageable));
    }

    /**
     * Post 생성
     *
     * @return ID
     */
    @ApiOperation(value = "모집글 생성", notes = "모집글을 생성한다.")
    @PostMapping
    public DataResponseDto<Long> createPost(@RequestBody PostsCreateRequestDto requestDto) {
        return DataResponseDto.of(postsService.savePost(requestDto));
    }

    /**
     * Post 수정
     *
     * @return ID
     */
    @ApiOperation(value = "모집글 수정", notes = "모집글의 ID 값을 통해 업데이트 한다.")
    @PutMapping("/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String username = ((UserDetails) principal).getUsername();
        String password = ((UserDetails) principal).getPassword();
        return postsService.update(id, requestDto);
    }

    /**
     * Post 삭제
     *
     * @return ID
     */
    @ApiOperation(value = "모집글 삭제", notes = "모집글의 ID 값을 통해 삭제")
    @DeleteMapping("/{id}")
    public Long deletePost(@PathVariable Long id) {
        return postsService.delete(id);
    }

    /**
     * Post 상세 조회
     *
     * @return ResponseEntity<PostResponse>
     */
    @ApiOperation(value = "모집글 상세조회", notes = "모집글의 ID 값을 통해 상세 조회")
    @GetMapping("/{id}")
    public DataResponseDto<PostsResponseDto> GetPost(@PathVariable Long id, HttpServletRequest req, HttpServletResponse res) {
        /* 조회수 로직 */
        viewCountUp(id, req, res);
        return DataResponseDto.of(postsService.findById(id));
    }

    private void viewCountUp(Long id, HttpServletRequest req, HttpServletResponse res) {
        Cookie oldCookie = null;

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("post")) {
                    oldCookie = cookie;
                }
            }
        }

        if (oldCookie != null) {
            if (!oldCookie.getValue().contains("[" + id.toString() + "]")) {
                postsService.updateView(id);
                oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24);
                res.addCookie(oldCookie);
            }
        } else {
            postsService.updateView(id);
            Cookie newCookie = new Cookie("boardView", "[" + id + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            res.addCookie(newCookie);
        }
    }

}
