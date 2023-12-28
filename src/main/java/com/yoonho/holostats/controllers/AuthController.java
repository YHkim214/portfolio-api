package com.yoonho.holostats.controllers;

import com.yoonho.holostats.common.CommonController;
import com.yoonho.holostats.common.ResponseEntityWrapper;
import com.yoonho.holostats.dtos.request.LoginRequestDto;
import com.yoonho.holostats.dtos.request.RegisterMemberRequestDto;
import com.yoonho.holostats.dtos.response.LoginResponseDto;
import com.yoonho.holostats.services.AuthService;
import com.yoonho.holostats.services.MemberService;
import org.springframework.http.ResponseEntity;
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
public class AuthController extends CommonController {

    private final MemberService memberService;
    private final AuthService authService;

    public AuthController(MemberService memberService, AuthService authService) {
        this.memberService = memberService;
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity register(@RequestBody RegisterMemberRequestDto registerMemberRequestDto) {

        memberService.registerMember(registerMemberRequestDto);

        return ResponseEntityWrapper.success(null);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto) {

        LoginResponseDto loginResponseDto = authService.login(loginRequestDto);

        return ResponseEntityWrapper.success(loginResponseDto);
    }
}
