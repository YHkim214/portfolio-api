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

import com.yoonho.holoboard.common.CommonCodes;
import com.yoonho.holoboard.common.PageInfo;
import com.yoonho.holoboard.dtos.BbsDto;
import com.yoonho.holoboard.dtos.request.*;
import com.yoonho.holoboard.dtos.response.GetBbsListResponseDto;
import com.yoonho.holoboard.exceptions.ApiException;
import com.yoonho.holoboard.models.Bbs;
import com.yoonho.holoboard.models.Member;
import com.yoonho.holoboard.repositories.BbsRepository;
import com.yoonho.holoboard.repositories.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public GetBbsListResponseDto getBbsList(String memberName, GetBbsListRequestDto getBbsListRequestDto) {
        Optional<Member> member = memberRepository.getMemberByName(memberName);

        if(member.isPresent()) getBbsListRequestDto.setMemberId(member.get().getMemberId());

        List<Bbs> bbsList = bbsRepository
                .getBbsByLsId(getBbsListRequestDto.getLsId(),
                        PageInfo.build(getBbsListRequestDto.getPage(), getBbsListRequestDto.getSize()),
                        getBbsListRequestDto.getMemberId());

        return new GetBbsListResponseDto(bbsList.size(), bbsList.stream().map(bbs -> BbsDto.build(bbs)).toList());
    }

    @Override
    public void insertBbs(String memberName, InsertBbsRequestDto insertBbsRequestDto) {
        Member member = memberRepository.getMemberByName(memberName)
                .orElseThrow(() -> new ApiException(999, "회원정보가 존재하지 않습니다."));

        Bbs bbs = new Bbs();
        bbs.setMemberId(member.getMemberId());
        bbs.setLsId(insertBbsRequestDto.getLsId());
        bbs.setBbsContent(insertBbsRequestDto.getBbsContent());
        bbs.setBbsType(insertBbsRequestDto.getBbsParentId() == null
                ? CommonCodes.BBS_TYPE.NORMAL.CODE : CommonCodes.BBS_TYPE.REPLY.CODE);
        bbs.setBbsParentId(insertBbsRequestDto.getBbsParentId());
        bbs.setBbsStatus(CommonCodes.BBS_STATUS.PUBLIC.CODE);

        bbsRepository.insertBbs(bbs);
    }

    @Override
    public void recommend(String memberName, RecommendRequestDto recommendRequestDto) {
        Member member = memberRepository.getMemberByName(memberName)
                .orElseThrow(() -> new ApiException(999, "회원정보가 존재하지 않습니다."));

        if(recommendRequestDto.isRecommend()) {
            bbsRepository.recommend(recommendRequestDto.getBbsId(), member.getMemberId());
        } else {
            bbsRepository.cancelRecommend(recommendRequestDto.getBbsId(), member.getMemberId());
        }
    }

    @Override
    public void updateBbs(String memberName, UpdateBbsRequestDto updateBbsRequestDto) {
        Member member = memberRepository.getMemberByName(memberName)
                .orElseThrow(() -> new ApiException(999, "회원정보가 존재하지 않습니다."));

        Bbs bbsDb = bbsRepository.getBbsById(updateBbsRequestDto.getBbsId())
                .orElseThrow(() -> new ApiException(999, "게시글 정보가 존재하지 않습니다."));

        if(!bbsDb.getMemberId().equals(member.getMemberId())) {
            throw new ApiException(999, "본인의 작성글만 수정 가능합니다.");
        }

        bbsRepository.updateBbs(updateBbsRequestDto.getContent(), updateBbsRequestDto.getBbsId());
    }

    @Override
    public void deleteBbs(String memberName, DeleteBbsRequestDto deleteBbsRequestDto) {
        Member member = memberRepository.getMemberByName(memberName)
                .orElseThrow(() -> new ApiException(999, "회원정보가 존재하지 않습니다."));

        Bbs bbsDb = bbsRepository.getBbsById(deleteBbsRequestDto.getBbsId())
                .orElseThrow(() -> new ApiException(999, "게시글 정보가 존재하지 않습니다."));

        if(!bbsDb.getMemberId().equals(member.getMemberId())) {
            throw new ApiException(999, "본인이 작성한 글만 삭제 가능합니다.");
        }

        //게시글 삭제
        bbsRepository.deleteBbs(deleteBbsRequestDto.getBbsId());

        //추천내역 삭제
        bbsRepository.deleteRecommend(deleteBbsRequestDto.getBbsId());
    }
}
