package com.yoonho.holostats.common;

import com.yoonho.holostats.exceptions.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * packageName    : com.yoonho.holostats.common
 * fileName       : CommonController
 * author         : kim-yoonho
 * date           : 12/27/23
 * description    : 상수
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/27/23        kim-yoonho       최초 생성
 */

public class CommonController {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity handleApiException(ApiException apiException) {
        return ResponseEntityWrapper.fail(apiException.getErrorCode(), apiException.getErrorMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity handleBadCredentialsException(BadCredentialsException badCredentialsException) {
        return ResponseEntityWrapper.fail(999, badCredentialsException.getMessage());
    }

}
