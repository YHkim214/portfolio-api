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

package com.yoonho.holostats.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * packageName    : com.yoonho.holostats.dtos.response
 * fileName       : RefreshResponseDto
 * author         : kim-yoonho
 * date           : 1/10/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/10/24        kim-yoonho       최초 생성
 */
@Data
@AllArgsConstructor
public class RefreshResponseDto {
    private String newAccessToken;
}
