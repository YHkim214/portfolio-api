package com.yoonho.holoboard.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.yoonho.holostats.common
 * fileName       : ResponseHeader
 * author         : kim-yoonho
 * date           : 12/27/23
 * description    : 공통 리스폰스 헤더
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/27/23        kim-yoonho       최초 생성
 */

@Getter
@Setter
@AllArgsConstructor
public class ResponseHeader {
    private String code;
    private String message;
}
