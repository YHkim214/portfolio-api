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
 * packageName    : com.yoonho.holoboard.dtos.request
 * fileName       : UpdateBbsRequestDto
 * author         : kim-yoonho
 * date           : 2/21/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/21/24        kim-yoonho       최초 생성
 */

@Data
public class UpdateBbsRequestDto {
    private Integer bbsId;
    private String content;
}
