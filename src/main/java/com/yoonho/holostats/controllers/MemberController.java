package com.yoonho.holostats.controllers;

import com.yoonho.holostats.common.CommonController;
import com.yoonho.holostats.common.ResponseEntityWrapper;
import com.yoonho.holostats.configs.security.JwtGenerator;
import com.yoonho.holostats.dtos.response.GetMemberInfoResponseDto;
import com.yoonho.holostats.services.member.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.yoonho.holostats.controllers
 * fileName       : AuthController
 * author         : kim-yoonho
 * date           : 12/26/23
 * description    : 회원 정보 객체
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/26/23        kim-yoonho       최초 생성
 */

@RestController
@RequestMapping("/api/member")
public class MemberController extends CommonController {

    private final JwtGenerator jwtGenerator;
    private final MemberService memberService;

    public MemberController(JwtGenerator jwtGenerator, MemberService memberService) {
        this.jwtGenerator = jwtGenerator;
        this.memberService = memberService;
    }

    @GetMapping("/getMemberInfo")
    public ResponseEntity getMemberInfo(@RequestHeader("Authorization") String accessToken) {
        String userName = jwtGenerator.getUserName(accessToken.substring(7, accessToken.length()));
        GetMemberInfoResponseDto getMemberInfoResponseDto = memberService.getMemberByName(userName);

        return ResponseEntityWrapper.success(getMemberInfoResponseDto);
    }

}
