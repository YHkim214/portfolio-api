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

}
