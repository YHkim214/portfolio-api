package com.yoonho.holostats.dtos.response;

import lombok.Data;

/**
 * packageName    : com.yoonho.holostats.dtos.response
 * fileName       : LoginResponseDto
 * author         : kim-yoonho
 * date           : 12/28/23
 * description    : 로그인 응답 dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/28/23        kim-yoonho       최초 생성
 */
@Data
public class LoginResponseDto {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer ";

    public LoginResponseDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
