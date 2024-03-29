package com.yoonho.holoboard.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yoonho.holoboard.common.CommonController;
import com.yoonho.holoboard.common.ResponseEntityWrapper;
import com.yoonho.holoboard.dtos.request.LoginRequestDto;
import com.yoonho.holoboard.dtos.request.RefreshRequestDto;
import com.yoonho.holoboard.dtos.request.RegisterMemberRequestDto;
import com.yoonho.holoboard.dtos.response.LoginResponseDto;
import com.yoonho.holoboard.dtos.response.RefreshResponseDto;
import com.yoonho.holoboard.services.auth.AuthService;
import com.yoonho.holoboard.services.member.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * packageName    : com.yoonho.holostats.controllers
 * fileName       : AuthController
 * author         : kim-yoonho
 * date           : 12/26/23
 * description    : 인증 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/26/23        kim-yoonho       최초 생성
 */

@RestController
@RequestMapping("/api/auth")
public class AuthController extends CommonController {

    private final MemberService memberService;
    private final AuthService authService;

    public AuthController(MemberService memberService, AuthService authService) {
        this.memberService = memberService;
        this.authService = authService;
    }

    /** 회원가입 **/
    @PostMapping(value = "/register", consumes = "multipart/form-data")
    @ResponseBody
    public ResponseEntity register(@ModelAttribute RegisterMemberRequestDto registerMemberRequestDto) throws IOException {

        memberService.registerMember(registerMemberRequestDto);

        return ResponseEntityWrapper.success(null);
    }

    /** 로그인 **/
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto) {

        LoginResponseDto loginResponseDto = authService.login(loginRequestDto);

        return ResponseEntityWrapper.success(loginResponseDto);
    }

    /** JWT토큰 리프레쉬 **/
    @PostMapping("/refresh")
    @ResponseBody
    public ResponseEntity refresh(@RequestBody RefreshRequestDto refreshRequestDto) throws JsonProcessingException {
        String newAccessToken = authService.refresh(refreshRequestDto.getOldAccessToken(), refreshRequestDto.getRefreshToken());

        return ResponseEntityWrapper.success(new RefreshResponseDto(newAccessToken));
    }
}
