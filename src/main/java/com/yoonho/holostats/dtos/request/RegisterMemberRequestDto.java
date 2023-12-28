package com.yoonho.holostats.dtos.request;

import lombok.Data;

import java.io.File;

/**
 * packageName    : com.yoonho.holostats.dtos.request
 * fileName       : RegisterMemberRequestDto
 * author         : kim-yoonho
 * date           : 12/27/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/27/23        kim-yoonho       최초 생성
 */

@Data
public class RegisterMemberRequestDto {
    private String memberName;
    private String memberPassword;
    private String memberNickName;
    private File memberThumbnailFile;
}
