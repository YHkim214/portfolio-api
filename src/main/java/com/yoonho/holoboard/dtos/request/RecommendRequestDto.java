package com.yoonho.holoboard.dtos.request;

import lombok.Data;

/**
 * packageName    : com.yoonho.holoboard.dtos.request
 * fileName       : RecommandRequestDto
 * author         : kim-yoonho
 * date           : 2/8/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/8/24        kim-yoonho       최초 생성
 */
@Data
public class RecommendRequestDto {
    private boolean isRecommend;
    private Integer bbsId;
    private Integer memberId;
}
