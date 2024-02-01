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

package com.yoonho.holostats.services.bbs;

import com.yoonho.holostats.common.PageInfo;
import com.yoonho.holostats.dtos.BbsDto;
import com.yoonho.holostats.dtos.request.GetBbsListRequestDto;
import com.yoonho.holostats.dtos.response.GetBbsListResponseDto;
import com.yoonho.holostats.models.Bbs;
import com.yoonho.holostats.repositories.BbsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName    : com.yoonho.holostats.services.bbs
 * fileName       : BbsServiceImpl
 * author         : kim-yoonho
 * date           : 1/31/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/31/24        kim-yoonho       최초 생성
 */
@Service
@Slf4j
public class BbsServiceImpl implements BbsService{
    private final BbsRepository bbsRepository;

    public BbsServiceImpl(BbsRepository bbsRepository) {
        this.bbsRepository = bbsRepository;
    }

    @Override
    public GetBbsListResponseDto getBbsList(GetBbsListRequestDto getBbsListRequestDto) {
        List<Bbs> bbsList = bbsRepository
                .getBbsByLsId(getBbsListRequestDto.getLsId(),
                        PageInfo.build(getBbsListRequestDto.getPage(), getBbsListRequestDto.getSize()));

        return new GetBbsListResponseDto(bbsList.size(), bbsList.stream().map(BbsDto::toBbsDto).toList());
    }
}
