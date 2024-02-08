/*
 * *
 *  *packageName    : ${PACKAGE_NAME}
 *  * fileName       : ${NAME}
 *  * author         : ${USER}
 *  * date           : ${DATE}
 *  * description    :
 *  * ===========================================================
 *  * DATE              AUTHOR             NOTE
 *  * -----------------------------------------------------------
 *  * ${DATE}        ${USER}       최초 생성
 *
 */

package com.yoonho.holoboard.dtos.request;

import lombok.Data;

/**
 * packageName    : com.yoonho.holostats.dtos.request
 * fileName       : ChangePasswordDto
 * author         : kim-yoonho
 * date           : 1/26/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/26/24        kim-yoonho       최초 생성
 */
@Data
public class ChangePasswordRequestDto {
    private String prevPassword;
    private String newPassword;
}
