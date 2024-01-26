package com.yoonho.holostats.common;

import com.yoonho.holostats.exceptions.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * packageName    : com.yoonho.holostats.common
 * fileName       : CommonController
 * author         : kim-yoonho
 * date           : 12/27/23
 * description    : 공통 예외처리를 위한 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/27/23        kim-yoonho       최초 생성
 */

public class CommonController {

    /** 프로젝트에서 정의한 예외 **/
    @ExceptionHandler(ApiException.class)
    public ResponseEntity handleApiException(ApiException apiException) {
        return ResponseEntityWrapper.fail(apiException.getErrorCode(), apiException.getErrorMessage());
    }

    /** 보안 인증관련 예외 **/
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity handleBadCredentialsException(BadCredentialsException badCredentialsException) {
        return ResponseEntityWrapper.fail(999, badCredentialsException.getMessage());
    }

    /** 유효성 검증 실패예외 **/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        return ResponseEntityWrapper.fail(999, methodArgumentNotValidException.getMessage());
    }

    /** 기타 예외 **/
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleGeneralException(Exception exception) {
        return ResponseEntityWrapper.fail(999, exception.getMessage());
    }

}
