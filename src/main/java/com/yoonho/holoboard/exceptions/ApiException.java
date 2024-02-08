package com.yoonho.holoboard.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.yoonho.holostats.exceptions
 * fileName       : ApiException
 * author         : kim-yoonho
 * date           : 12/21/23
 * description    : api 관련 예외 처리
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/21/23        kim-yoonho       최초 생성
 */

@Getter
@Setter
public class ApiException extends RuntimeException{
    private Integer errorCode;
    private String errorMessage;

    public ApiException(Integer errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
