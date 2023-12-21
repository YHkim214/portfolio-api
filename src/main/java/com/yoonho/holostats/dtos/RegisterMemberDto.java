package com.yoonho.holostats.dtos;

import lombok.Data;

import java.io.File;

@Data
public class RegisterMemberDto {
    private String memberName;
    private String memberPassword;
    private String memberNickName;
    private File memberThumbnailFile;
}
