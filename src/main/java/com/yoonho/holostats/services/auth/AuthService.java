/*
 * *
 *  *packageName    : ${PACKAGE_NAME}
 *  * fileName       : ${NAME}
 *  * author         : ${USER}
 *  * date           : ${DATE}
 *  * description    :
 *  * ===========================================================
 *  * DATE              AUTHOR             NOTE
 *  * -----------------------------------------------------------
 *  * ${DATE}        ${USER}       최초 생성
 *
 */

package com.yoonho.holostats.services.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
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
    String refresh(String oldAccessToken, String refreshToken) throws JsonProcessingException;

}
