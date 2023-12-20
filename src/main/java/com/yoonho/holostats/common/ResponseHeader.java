package com.yoonho.holostats.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseHeader {
    private String code;
    private String message;
}
