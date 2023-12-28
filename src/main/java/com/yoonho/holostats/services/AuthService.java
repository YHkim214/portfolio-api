package com.yoonho.holostats.services;

import com.yoonho.holostats.dtos.request.LoginRequestDto;
import com.yoonho.holostats.dtos.response.LoginResponseDto;

/**
 * packageName    : com.yoonho.holostats.services
 * fileName       : AuthService
 * author         : kim-yoonho
 * date           : 12/27/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/27/23        kim-yoonho       최초 생성
 */
public interface AuthService {

    LoginResponseDto login(LoginRequestDto loginRequestDto);

}
