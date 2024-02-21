package com.yoonho.holoboard.repositories;

import com.yoonho.holoboard.models.RefreshToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * packageName    : com.yoonho.holostats.repositories
 * fileName       : RefreshTokenDto
 * author         : kim-yoonho
 * date           : 1/9/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/9/24        kim-yoonho       최초 생성
 */

@Mapper
public interface RefreshTokenRepository {
    void insertRefreshToken(@Param("refreshToken") RefreshToken refreshToken);
    Optional<RefreshToken> getRefreshTokenByMemberId(@Param("memberId") Integer memberId);
    void updateRefreshToken(@Param("refreshToken") RefreshToken refreshToken);
}
