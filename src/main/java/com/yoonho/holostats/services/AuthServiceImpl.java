package com.yoonho.holostats.services;

import com.yoonho.holostats.dtos.LoginDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.yoonho.holostats.services
 * fileName       : AuthServiceImpl
 * author         : kim-yoonho
 * date           : 12/27/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/27/23        kim-yoonho       최초 생성
 */
@Service
public class AuthServiceImpl implements AuthService{

    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void login(LoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getMemberName(), loginDto.getMemberPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
