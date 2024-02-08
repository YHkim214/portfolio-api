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

import lombok.Data;

/**
 * packageName    : com.yoonho.holostats.dtos.response
 * fileName       : GetMemberInfoDto
 * author         : kim-yoonho
 * date           : 1/5/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/5/24        kim-yoonho       최초 생성
 */

@Data
public class GetMemberInfoResponseDto {
    private Integer memberId;
    private String memberName;
    private String memberNickName;
    private String memberThumbnailFileUrl;
}
