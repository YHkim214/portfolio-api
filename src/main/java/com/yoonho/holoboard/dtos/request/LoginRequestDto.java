package com.yoonho.holoboard.dtos.request;

import lombok.Data;

/**
 * packageName    : com.yoonho.holostats.dtos.request
 * fileName       : LoginRequestDto
 * author         : kim-yoonho
 * date           : 12/27/23
 * description    : 로그인 요청 dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/27/23        kim-yoonho       최초 생성
 */
@Data
public class LoginRequestDto {
    private String memberName;
    private String memberPassword;
}
