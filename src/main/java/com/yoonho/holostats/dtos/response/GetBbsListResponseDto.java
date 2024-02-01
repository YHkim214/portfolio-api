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

import com.yoonho.holostats.dtos.BbsDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * packageName    : com.yoonho.holostats.dtos.response
 * fileName       : GetBbsResponseDto
 * author         : kim-yoonho
 * date           : 2/1/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/1/24        kim-yoonho       최초 생성
 */
@Data
@AllArgsConstructor
public class GetBbsListResponseDto {
    private Integer resultCount;
    List<BbsDto> bbsList;
}
