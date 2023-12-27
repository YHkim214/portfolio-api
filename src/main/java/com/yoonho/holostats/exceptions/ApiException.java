package com.yoonho.holostats.exceptions;

import lombok.Getter;
import lombok.Setter;

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
