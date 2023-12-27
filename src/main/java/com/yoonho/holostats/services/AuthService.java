package com.yoonho.holostats.services;

import com.yoonho.holostats.dtos.LoginDto;

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

    public void login(LoginDto loginDto);

}
