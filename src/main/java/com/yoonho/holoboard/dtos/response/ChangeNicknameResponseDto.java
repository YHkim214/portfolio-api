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

package com.yoonho.holoboard.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * packageName    : com.yoonho.holostats.dtos.response
 * fileName       : ChangeNicknameResponseDto
 * author         : kim-yoonho
 * date           : 1/26/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/26/24        kim-yoonho       최초 생성
 */
@Data
@AllArgsConstructor
public class ChangeNicknameResponseDto {
    private String memberNickname;
}
