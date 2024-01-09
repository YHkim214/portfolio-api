package com.yoonho.holostats.services;

import com.yoonho.holostats.configs.security.JwtGenerator;
import com.yoonho.holostats.dtos.request.LoginRequestDto;
import com.yoonho.holostats.dtos.response.LoginResponseDto;
import com.yoonho.holostats.exceptions.ApiException;
import com.yoonho.holostats.models.Member;
import com.yoonho.holostats.models.RefreshToken;
import com.yoonho.holostats.repositories.MemberRepository;
import com.yoonho.holostats.repositories.RefreshTokenRepository;
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

}
