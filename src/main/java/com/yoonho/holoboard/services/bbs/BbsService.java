package com.yoonho.holoboard.services.bbs;

import com.yoonho.holoboard.dtos.request.*;
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

    GetBbsListResponseDto getBbsList(String memberName, GetBbsListRequestDto getBbsListRequestDto);
    void insertBbs(String memberName, InsertBbsRequestDto insertBbsRequestDto);

    void recommend(String memberName, RecommendRequestDto recommendRequestDto);
    void updateBbs(String memberName, UpdateBbsRequestDto updateBbsRequestDto);
    void deleteBbs(String memberName, DeleteBbsRequestDto deleteBbsRequestDto);

}
