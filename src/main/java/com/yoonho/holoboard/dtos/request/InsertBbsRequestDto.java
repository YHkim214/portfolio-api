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
 * fileName       : InsertBbsRequestDto
 * author         : kim-yoonho
 * date           : 2/1/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/1/24        kim-yoonho       최초 생성
 */
@Data
public class InsertBbsRequestDto {
    private Integer memberId;
    private Integer lsId;
    private String bbsContent;
    private String bbsType;
    private Integer bbsParentId;
    private String bbsStatus;
}
