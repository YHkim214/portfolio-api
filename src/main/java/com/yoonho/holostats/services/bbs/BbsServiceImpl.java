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

import com.yoonho.holostats.common.CommonCodes;
import com.yoonho.holostats.common.PageInfo;
import com.yoonho.holostats.dtos.BbsDto;
import com.yoonho.holostats.dtos.request.GetBbsListRequestDto;
import com.yoonho.holostats.dtos.request.InsertBbsRequestDto;
import com.yoonho.holostats.dtos.response.GetBbsListResponseDto;
import com.yoonho.holostats.exceptions.ApiException;
import com.yoonho.holostats.models.Bbs;
import com.yoonho.holostats.models.Member;
import com.yoonho.holostats.repositories.BbsRepository;
import com.yoonho.holostats.repositories.MemberRepository;
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
    private final MemberRepository memberRepository;

    public BbsServiceImpl(BbsRepository bbsRepository, MemberRepository memberRepository) {
        this.bbsRepository = bbsRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public GetBbsListResponseDto getBbsList(GetBbsListRequestDto getBbsListRequestDto) {
        List<Bbs> bbsList = bbsRepository
                .getBbsByLsId(getBbsListRequestDto.getLsId(),
                        PageInfo.build(getBbsListRequestDto.getPage(), getBbsListRequestDto.getSize()));

        return new GetBbsListResponseDto(bbsList.size(), bbsList.stream().map(BbsDto::toBbsDto).toList());
    }

    @Override
    public void insertBbs(String memberName, InsertBbsRequestDto insertBbsRequestDto) {
        Member member = memberRepository.getMemberByName(memberName)
                .orElseThrow(() -> new ApiException(999, "회원정보가 존재하지 않습니다."));

        Bbs bbs = new Bbs();
        bbs.setMemberId(member.getMemberId());
        bbs.setLsId(insertBbsRequestDto.getLsId());
        bbs.setBbsContent(insertBbsRequestDto.getBbsContent());
        bbs.setBbsGoodCnt(0);
        bbs.setBbsType(insertBbsRequestDto.getBbsParentId() == null
                ? CommonCodes.BBS_TYPE.NORMAL.CODE : CommonCodes.BBS_TYPE.REPLY.CODE);
        bbs.setParentId(insertBbsRequestDto.getBbsParentId());
        bbs.setBbsStatus(CommonCodes.BBS_STATUS.PUBLIC.CODE);

        bbsRepository.insertBbs(bbs);
    }
}
