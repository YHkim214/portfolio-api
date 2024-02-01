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

package com.yoonho.holostats.dtos;

import com.yoonho.holostats.models.Bbs;
import lombok.Data;

/**
 * packageName    : com.yoonho.holostats.dtos
 * fileName       : BbsDto
 * author         : kim-yoonho
 * date           : 2/1/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/1/24        kim-yoonho       최초 생성
 */
@Data
public class BbsDto {
    public static BbsDto toBbsDto(Bbs bbs) {
        return new BbsDto();
    }
}
