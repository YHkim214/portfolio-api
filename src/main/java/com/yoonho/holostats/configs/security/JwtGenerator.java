package com.yoonho.holostats.configs.security;

import com.yoonho.holostats.common.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

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
    public String generateToken(Authentication authentication) {
        String userName = authentication.getName();
        Date curDate = new Date();
        Date expDate = new Date(curDate.getTime() + Constants.JWT_EXPIRATION);

        return Jwts
            .builder()
            .setSubject(userName)
            .setIssuedAt(curDate)
            .setExpiration(expDate)
            .signWith(SignatureAlgorithm.HS512, Constants.JWT_SECRET)
            .compact();
    }

    public String getUserName(String token) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(Constants.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts
                .parser()
                .setSigningKey(Constants.JWT_SECRET)
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            //TODO 하드코딩 걷어내기
            throw new AuthenticationCredentialsNotFoundException("JWT토큰이 만료되었거나, 유효하지 않습니다.");
        }
    }
}
