package com.yoonho.holostats.dtos;

import lombok.Data;

/**
 * packageName    : com.yoonho.holostats.dtos
 * fileName       : LoginDto
 * author         : kim-yoonho
 * date           : 12/27/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/27/23        kim-yoonho       최초 생성
 */
@Data
public class LoginDto {
    private String memberName;
    private String memberPassword;
}
