package com.yoonho.holoboard.dtos.request;

import lombok.Data;

/**
 * packageName    : com.yoonho.holostats.dtos.request
 * fileName       : ChangeNicknameRequestDto
 * author         : kim-yoonho
 * date           : 1/26/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/26/24        kim-yoonho       최초 생성
 */
@Data
public class ChangeNicknameRequestDto {
    private String newNickname;
}
