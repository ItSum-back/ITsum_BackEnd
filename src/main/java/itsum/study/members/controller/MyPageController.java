package itsum.study.members.controller;

import itsum.study.members.service.MemberService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/mypage")
@RestController
public class MyPageController {

    private final MemberService memberService;

    public MyPageController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PatchMapping("/{id}")
    public boolean changeMemberNickName(@PathVariable Long id, @RequestBody String nickName) {

        return memberService.updateNickName(id, nickName);
    }

}
