package com.yoonho.holostats.controllers;

import com.yoonho.holostats.common.CommonController;
import com.yoonho.holostats.common.ResponseEntityWrapper;
import com.yoonho.holostats.configs.security.JwtGenerator;
import com.yoonho.holostats.dtos.request.ChangeNicknameRequestDto;
import com.yoonho.holostats.dtos.request.ChangePasswordDto;
import com.yoonho.holostats.dtos.response.ChangeNicknameResponseDto;
import com.yoonho.holostats.dtos.response.GetMemberInfoResponseDto;
import com.yoonho.holostats.services.member.MemberService;
import com.yoonho.holostats.utils.StringUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /** 회원정보 반환 **/
    @GetMapping("/getMemberInfo")
    public ResponseEntity getMemberInfo(@RequestHeader("Authorization") String accessToken) {
        String userName = jwtGenerator.getUserName(StringUtil.processRequestAccessToken(accessToken));
        GetMemberInfoResponseDto getMemberInfoResponseDto = memberService.getMemberByName(userName);

        return ResponseEntityWrapper.success(getMemberInfoResponseDto);
    }

    /** 닉네임 변경 **/
    @PostMapping("/changeNickname")
    public ResponseEntity changeNickname(@RequestHeader("Authorization") String accessToken,
                                         @RequestBody ChangeNicknameRequestDto changeNicknameRequestDto) {
        String userName = jwtGenerator.getUserName(StringUtil.processRequestAccessToken(accessToken));
        ChangeNicknameResponseDto changeNicknameResponseDto = memberService.changeNickname(userName, changeNicknameRequestDto);

        return ResponseEntityWrapper.success(changeNicknameResponseDto);
    }

    @PostMapping("/changePassword")
    public ResponseEntity changePassword(@RequestHeader("Authorization") String accessToken,
                                         @RequestBody ChangePasswordDto changePasswordDto) {
        String userName = jwtGenerator.getUserName(StringUtil.processRequestAccessToken(accessToken));
        memberService.changePassword(userName, changePasswordDto);

        return ResponseEntityWrapper.success(null);
    }

}
