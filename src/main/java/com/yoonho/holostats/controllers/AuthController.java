package com.yoonho.holostats.controllers;

import com.yoonho.holostats.common.CustomResponseEntity;
import com.yoonho.holostats.dtos.RegisterMemberDto;
import com.yoonho.holostats.services.MemberService;
import org.springframework.web.bind.annotation.*;

/**
 * packageName    : com.yoonho.holostats.controllers
 * fileName       : AuthController
 * author         : kim-yoonho
 * date           : 12/26/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/26/23        kim-yoonho       최초 생성
 */

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final MemberService memberService;

    public AuthController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    @ResponseBody
    public CustomResponseEntity register(@RequestBody RegisterMemberDto registerMemberDto) {

        memberService.registerMember(registerMemberDto);

        return CustomResponseEntity.success(null);
    }
}
