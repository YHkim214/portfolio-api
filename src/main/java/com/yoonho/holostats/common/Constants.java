package com.yoonho.holostats.common;

/**
 * packageName    : com.yoonho.holostats.common
 * fileName       : Constants
 * author         : kim-yoonho
 * date           : 12/27/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/27/23        kim-yoonho       최초 생성
 */
//TODO 밖으로 빼내기(보안이슈)
public class Constants {
    public static final long JWT_EXPIRATION = 3600L;
    public static final String JWT_SECRET = "secret";
}
