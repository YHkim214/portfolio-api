package com.yoonho.holoboard.dtos.request;

import lombok.Data;

/**
 * packageName    : com.yoonho.holostats.dtos.request
 * fileName       : RefreshRequestDto
 * author         : kim-yoonho
 * date           : 1/10/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/10/24        kim-yoonho       최초 생성
 */
@Data
public class RefreshRequestDto {
    private String oldAccessToken;
    private String refreshToken;
}
