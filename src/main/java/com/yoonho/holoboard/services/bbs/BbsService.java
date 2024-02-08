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

package com.yoonho.holoboard.services.bbs;

import com.yoonho.holoboard.dtos.request.GetBbsListRequestDto;
import com.yoonho.holoboard.dtos.request.InsertBbsRequestDto;
import com.yoonho.holoboard.dtos.response.GetBbsListResponseDto;

/**
 * packageName    : com.yoonho.holostats.services.bbs
 * fileName       : BbsService
 * author         : kim-yoonho
 * date           : 1/31/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/31/24        kim-yoonho       최초 생성
 */
public interface BbsService {

    GetBbsListResponseDto getBbsList(GetBbsListRequestDto getBbsListRequestDto);
    void insertBbs(String memberName, InsertBbsRequestDto insertBbsRequestDto);

}
