package com.yoonho.holoboard.controllers;

import com.yoonho.holoboard.common.CommonController;
import com.yoonho.holoboard.common.ResponseEntityWrapper;
import com.yoonho.holoboard.configs.security.JwtGenerator;
import com.yoonho.holoboard.dtos.request.ChangeNicknameRequestDto;
import com.yoonho.holoboard.dtos.request.ChangePasswordRequestDto;
import com.yoonho.holoboard.dtos.response.ChangeNicknameResponseDto;
import com.yoonho.holoboard.dtos.response.GetMemberInfoResponseDto;
import com.yoonho.holoboard.services.member.MemberService;
import com.yoonho.holoboard.utils.StringUtil;
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
@RequestMapping("/api")
public class MemberController extends CommonController {

    private final JwtGenerator jwtGenerator;
    private final MemberService memberService;

    public MemberController(JwtGenerator jwtGenerator, MemberService memberService) {
        this.jwtGenerator = jwtGenerator;
        this.memberService = memberService;
    }

    /** 회원정보 반환 **/
    @GetMapping("/member")
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

    /** 비밀번호 변경 **/
    @PostMapping("/changePassword")
    public ResponseEntity changePassword(@RequestHeader("Authorization") String accessToken,
                                         @RequestBody ChangePasswordRequestDto changePasswordRequestDto) {
        String userName = jwtGenerator.getUserName(StringUtil.processRequestAccessToken(accessToken));
        memberService.changePassword(userName, changePasswordRequestDto);

        return ResponseEntityWrapper.success(null);
    }

}
