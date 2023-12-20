package com.yoonho.holostats.controllers;

import com.yoonho.holostats.common.CustomController;
import com.yoonho.holostats.repositories.MemberRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController extends CustomController {

    private final AuthenticationManager authenticationManager;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberController(AuthenticationManager authenticationManager,
                            MemberRepository memberRepository,
                            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }
}
