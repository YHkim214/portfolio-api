package com.yoonho.holoboard.dtos.request;

import lombok.Data;

/**
 * packageName    : com.yoonho.holostats.dtos.request
 * fileName       : GetBbsRequestDto
 * author         : kim-yoonho
 * date           : 2/1/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/1/24        kim-yoonho       최초 생성
 */
@Data
public class GetBbsListRequestDto {
    private Integer lsId;
    private Integer page = 1;
    private Integer size = 20;
    private Integer memberId;
}
