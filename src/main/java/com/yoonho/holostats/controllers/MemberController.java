package com.yoonho.holostats.controllers;

import com.yoonho.holostats.common.ApiException;
import com.yoonho.holostats.common.CustomController;
import com.yoonho.holostats.common.CustomResponseEntity;
import com.yoonho.holostats.dtos.RegisterMemberDto;
import com.yoonho.holostats.models.Member;
import com.yoonho.holostats.repositories.MemberRepository;
import com.yoonho.holostats.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/member")
public class MemberController extends CustomController {

    private final AuthenticationManager authenticationManager;
    private final MemberRepository memberRepository;
    private final MemberService memberService;


    public MemberController(AuthenticationManager authenticationManager,
                            MemberRepository memberRepository,
                            MemberService memberService) {
        this.authenticationManager = authenticationManager;
        this.memberRepository = memberRepository;
        this.memberService = memberService;
    }

    @PostMapping("/register")
    @ResponseBody
    public CustomResponseEntity register(@RequestBody RegisterMemberDto registerMemberDto) throws Exception {

        Optional<Member> member = memberRepository.getMemberByName(registerMemberDto.getMemberName());

        if(member.isPresent()) {
            throw new ApiException("999", "이미 존재하는 회원 명 입니다.");
        }

        memberService.insertMember(registerMemberDto);

        return CustomResponseEntity.success(null);
    }
}
