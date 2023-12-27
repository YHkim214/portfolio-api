package com.yoonho.holostats.controllers;

import com.yoonho.holostats.exceptions.ApiException;
import com.yoonho.holostats.common.ResponseEntityWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class CustomController {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity handleApiException(ApiException apiException) {
        return ResponseEntityWrapper.fail(apiException.getErrorCode(), apiException.getErrorMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity handleBadCredentialsException(BadCredentialsException badCredentialsException) {
        return ResponseEntityWrapper.fail(999, badCredentialsException.getMessage());
    }

}
