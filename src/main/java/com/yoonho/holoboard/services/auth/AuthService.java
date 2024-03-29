package com.yoonho.holoboard.services.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yoonho.holoboard.dtos.request.LoginRequestDto;
import com.yoonho.holoboard.dtos.response.LoginResponseDto;

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
    String refresh(String oldAccessToken, String refreshToken) throws JsonProcessingException;

}
