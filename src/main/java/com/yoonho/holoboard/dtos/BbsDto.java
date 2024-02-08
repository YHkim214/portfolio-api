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

package com.yoonho.holoboard.dtos;

import com.yoonho.holoboard.models.Bbs;
import com.yoonho.holoboard.utils.CollectionUtil;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * packageName    : com.yoonho.holoboard.dtos
 * fileName       : BbsDto
 * author         : kim-yoonho
 * date           : 2/8/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/8/24        kim-yoonho       최초 생성
 */
@Data
public class BbsDto {

    private Integer bbsId;
    private Integer memberId;
    private Integer lsId;
    private String bbsContent;
    private Integer bbsGoodCnt;
    private String bbsType;
    private Integer parentId;
    private String bbsStatus;
    private Timestamp createTime;
    private Timestamp updateTime;

    private String memberNickname;

    private Boolean isRecommended;

    List<BbsDto> children;

    public static BbsDto build(Bbs bbs) {
        BbsDto bbsDto = new BbsDto();

        bbsDto.setBbsId(bbs.getBbsId());
        bbsDto.setMemberId(bbs.getMemberId());
        bbsDto.setLsId(bbs.getLsId());
        bbsDto.setBbsContent(bbs.getBbsContent());
        bbsDto.setBbsGoodCnt(bbs.getBbsGoodCnt());
        bbsDto.setBbsType(bbs.getBbsType());
        bbsDto.setParentId(bbs.getBbsParentId());
        bbsDto.setBbsStatus(bbs.getBbsStatus());
        bbsDto.setCreateTime(bbs.getCreateTime());
        bbsDto.setUpdateTime(bbs.getUpdateTime());

        bbsDto.setMemberNickname(bbs.getMember().getMemberNickName());

        if(!CollectionUtil.isNullOrEmpty(bbs.getChildren())) {
            bbsDto.setChildren(bbs.getChildren().stream().filter(b -> b.getBbsId() != null).map(b -> build(b)).toList());
        }

        bbsDto.setIsRecommended(bbs.getIsRecommended());

        return bbsDto;
    }

}
