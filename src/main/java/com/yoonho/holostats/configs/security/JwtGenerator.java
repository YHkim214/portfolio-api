package com.yoonho.holostats.configs.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.sf.jsqlparser.statement.select.KSQLWindow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.access-token.expiration-minutes}")
    private Integer accessTokenExpMin;

    @Value("${jwt.refresh-token.expiration-hours}")
    private Integer refreshTokenExpHours;

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

    public boolean validateToken(String token) {
        try {
            Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("JWT토큰이 만료되었거나, 유효하지 않습니다.");
        }
    }
}
