package com.yoonho.holostats.dtos.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.Serializable;

/**
 * packageName    : com.yoonho.holostats.dtos.request
 * fileName       : RegisterMemberRequestDto
 * author         : kim-yoonho
 * date           : 12/27/23
 * description    : 회원등록 dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/27/23        kim-yoonho       최초 생성
 */

@Data
public class RegisterMemberRequestDto implements Serializable {
    private String memberName;
    private String memberPassword;
    private String memberNickName;
    private MultipartFile memberThumbnailFile;
}
