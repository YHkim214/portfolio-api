package com.yoonho.holostats.dtos;

import lombok.Data;

/*TODO request response 객체 고도화 하기*/
@Data
public class RegisterDto {
    private String memberName;
    private String memberPassword;
}
