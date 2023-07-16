package itsum.study.members.controller;

import itsum.study.members.dto.NickNameChangeRequest;
import itsum.study.members.service.MemberService;
import itsum.study.utils.dto.DataResponseDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RequestMapping("/mypage")
@RestController
public class MyPageController {

    private final MemberService memberService;

    public MyPageController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PatchMapping("/{id}")
    public DataResponseDto changeMemberNickName(@PathVariable String id, @RequestBody NickNameChangeRequest request) {

        return DataResponseDto.of(memberService.updateNickName(id, request.getNickName()));
    }

}
