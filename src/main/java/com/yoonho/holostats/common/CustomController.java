package com.yoonho.holostats.common;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class CustomController {

    @ExceptionHandler(ApiException.class)
    public CustomResponseEntity<?> handleApiException(ApiException apiException) {
        return CustomResponseEntity.error(apiException.getErrorCode(), apiException.getErrorMessage());
    }

}
