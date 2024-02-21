package com.yoonho.holoboard.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * packageName    : com.yoonho.holostats.dtos.request
 * fileName       : GetLiveStreamRequestDto
 * author         : kim-yoonho
 * date           : 1/22/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/22/24        kim-yoonho       최초 생성
 */
@Data
@AllArgsConstructor
public class GetLiveStreamRequestDto {
    private String date; /** YYYY-MM-DD 형식 **/
}
