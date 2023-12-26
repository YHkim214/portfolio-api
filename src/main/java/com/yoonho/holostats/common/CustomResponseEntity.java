package com.yoonho.holostats.common;

public class CustomResponseEntity<T> {

    private ResponseHeader responseHeader;
    private T data;

    public static <T> CustomResponseEntity<T> success(T data) {
        return new CustomResponseEntity<>(new ResponseHeader(CommonCodes.ERROR_CODE.ERROR_CODE_NONE.VAL, CommonCodes.ERROR_CODE.ERROR_CODE_NONE.DESC), data);
    }

    public static CustomResponseEntity<?> error(String code, String message) {
        return new CustomResponseEntity<>(new ResponseHeader(code, message), null);
    }

    private CustomResponseEntity(ResponseHeader responseHeader, T data) {
        this.responseHeader = responseHeader;
        this.data = data;
    }
}
