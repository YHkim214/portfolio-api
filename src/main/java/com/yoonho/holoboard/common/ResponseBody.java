package com.yoonho.holoboard.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * packageName    : com.yoonho.holostats.common
 * fileName       : ResponseBody
 * author         : kim-yoonho
 * date           : 12/27/23
 * description    : 공통 리스폰스 바디
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/27/23        kim-yoonho       최초 생성
 */
@Data
@AllArgsConstructor
public class ResponseBody<T> {
    private ResponseHeader header;
    private T data;

    public ResponseBody(T data, String code, String message) {
        this.header = new ResponseHeader(code, message);
        this.data = data;
    }
}
