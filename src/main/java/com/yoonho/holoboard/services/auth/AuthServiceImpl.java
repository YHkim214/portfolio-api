package com.yoonho.holoboard.services.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yoonho.holoboard.configs.security.JwtGenerator;
import com.yoonho.holoboard.dtos.request.LoginRequestDto;
import com.yoonho.holoboard.dtos.response.LoginResponseDto;
import com.yoonho.holoboard.exceptions.ApiException;
import com.yoonho.holoboard.models.Member;
import com.yoonho.holoboard.models.RefreshToken;
import com.yoonho.holoboard.repositories.MemberRepository;
import com.yoonho.holoboard.repositories.RefreshTokenRepository;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class AuthServiceImpl implements AuthService{

    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           JwtGenerator jwtGenerator,
                           MemberRepository memberRepository,
                           RefreshTokenRepository refreshTokenRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        this.memberRepository = memberRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getMemberName(), loginRequestDto.getMemberPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Member member = memberRepository.getMemberByName(authentication.getName())
                .orElseThrow(() -> new ApiException(999, "회원 정보가 존재하지 않습니다."));

        String token = jwtGenerator.generateToken(authentication);
        String refreshToken = jwtGenerator.generateRefreshToken();

        refreshTokenRepository.getRefreshTokenByMemberId(member.getMemberId())
                .ifPresentOrElse(
                        v -> refreshTokenRepository.updateRefreshToken(new RefreshToken(refreshToken, member.getMemberId())),
                        () -> refreshTokenRepository.insertRefreshToken(new RefreshToken(refreshToken, member.getMemberId()))
                );

        return new LoginResponseDto(token, refreshToken);
    }

    @Override
    public String refresh(String oldAccessToken, String refreshToken) throws JsonProcessingException {
        jwtGenerator.validateRefreshToken(refreshToken, oldAccessToken);
        String newAccessToken = jwtGenerator.recreateAccessToken(oldAccessToken);

        return newAccessToken;
    }
}
