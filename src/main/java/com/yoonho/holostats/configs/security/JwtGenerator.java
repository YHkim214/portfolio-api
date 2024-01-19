package com.yoonho.holostats.configs.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoonho.holostats.exceptions.ApiException;
import com.yoonho.holostats.models.Member;
import com.yoonho.holostats.models.RefreshToken;
import com.yoonho.holostats.repositories.MemberRepository;
import com.yoonho.holostats.repositories.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

/**
 * packageName    : com.yoonho.holostats.configs.security
 * fileName       : JwtGenerator
 * author         : kim-yoonho
 * date           : 12/27/23
 * description    : Jwt 토큰 생성 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/27/23        kim-yoonho       최초 생성
 */
@Component
public class JwtGenerator {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.access-token.expiration-minutes}")
    private Integer accessTokenExpMin;

    @Value("${jwt.refresh-token.expiration-hours}")
    private Integer refreshTokenExpHours;

    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JwtGenerator(RefreshTokenRepository refreshTokenRepository, MemberRepository memberRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.memberRepository = memberRepository;
    }

    public String generateToken(Authentication authentication) {
        String userName = authentication.getName();

        return Jwts
            .builder()
            .setSubject(userName)
            .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
            .setExpiration(Date.from(Instant.now().plus(accessTokenExpMin, ChronoUnit.MINUTES)))
            .signWith(SignatureAlgorithm.HS512, secretKey)
            .compact();
    }

    public String generateToken(String memberName) {

        return Jwts
                .builder()
                .setSubject(memberName)
                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
                .setExpiration(Date.from(Instant.now().plus(accessTokenExpMin, ChronoUnit.MINUTES)))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public String generateRefreshToken() {
        return Jwts
                .builder()
                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
                .setExpiration(Date.from(Instant.now().plus(refreshTokenExpHours, ChronoUnit.HOURS)))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public String getUserName(String token) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        String result = claims.getSubject();
        return result;
    }

    public void validateToken(String token) {
        try {
            Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("JWT토큰이 만료되었거나, 유효하지 않습니다.");
        }
    }

    public void validateRefreshToken(String refreshToken, String oldAccessToken) throws JsonProcessingException {
        validateAndParseToken(refreshToken);
        String memberName =  decodeJwtPayloadSubject(oldAccessToken);
        Member member = memberRepository.getMemberByName(memberName).orElseThrow(() -> new ApiException(999, "회원정보를 찾을 수 없습니다."));
        Optional<RefreshToken> refreshTokenDb = refreshTokenRepository.getRefreshTokenByMemberId(member.getMemberId());
        refreshTokenDb.orElseThrow(() -> new ApiException(999, "리프레쉬 토큰이 존재하지 않습니다."));

        RefreshToken refreshTokenDbVal = refreshTokenDb.get();

        if(!refreshTokenDbVal.getRefreshToken().equals(refreshToken.trim())) {
            throw new ApiException(999, "리프레쉬 토큰이 일치하지 않습니다.");
        }
    }

    private Jws<Claims> validateAndParseToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
    }

    private String decodeJwtPayloadSubject(String oldAccessToken) throws JsonProcessingException {
        return objectMapper.readValue(new String(Base64.getDecoder().decode(oldAccessToken.split("\\.")[1]), StandardCharsets.UTF_8), Map.class)
                .get("sub").toString();
    }

    public String recreateAccessToken(String oldAccessToken) throws JsonProcessingException {
        String memberName = decodeJwtPayloadSubject(oldAccessToken);
        return generateToken(memberName);
    }

    public String getMemberNameFromToken(String token) throws JsonProcessingException {
        return decodeJwtPayloadSubject(token);
    }
}
