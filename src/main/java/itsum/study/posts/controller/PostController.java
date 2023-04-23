package itsum.study.auth.controller;

import io.swagger.annotations.ApiOperation;
import itsum.study.auth.dto.AuthRequest;
import itsum.study.auth.dto.AuthResponse;
import itsum.study.auth.jwt.AuthToken;
import itsum.study.auth.jwt.JwtHeaderUtil;
import itsum.study.utils.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    /**
     * Post 조회
     * @return ResponseEntity<PostResponse>
     */
//    @ApiOperation(value = "게시글 상세조회", notes = "게시글의 ID 값을 통해 상세 조회")
//    @PostMapping(value = "/post/{ID}")
//    public ResponseEntity<PostResponse> kakaoAuthRequest(@RequestBody PostRequest postRequest) {
//        return ApiResponse.success(postService.get(postRequest));
//    }

    /**
     * Post 수정
     * @return ResponseEntity<PostResponse>
     */

    /**
     * Post 삭제
     * @return ResponseEntity<PostResponse>
     */

}
