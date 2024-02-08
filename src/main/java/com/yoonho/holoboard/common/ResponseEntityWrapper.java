package com.yoonho.holoboard.common;

import org.springframework.http.ResponseEntity;

/**
 * packageName    : com.yoonho.holostats.common
 * fileName       : ResponseEntitu
 * author         : kim-yoonho
 * date           : 12/27/23
 * description    : 스프링 리스폰스 객체 래퍼
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/27/23        kim-yoonho       최초 생성
 */
public class ResponseEntityWrapper {

    public static <T> ResponseEntity<ResponseBody<T>> success(T data) {
        return ResponseEntity
                .ok(new ResponseBody<T>(data, CommonCodes.ERROR_CODE.NONE.VAL, CommonCodes.ERROR_CODE.NONE.DESC));
    }

    public static ResponseEntity fail(Integer code, String message) {
        return ResponseEntity
                .badRequest()
                .body(new ResponseBody(null, String.valueOf(code), message));
    }

}
