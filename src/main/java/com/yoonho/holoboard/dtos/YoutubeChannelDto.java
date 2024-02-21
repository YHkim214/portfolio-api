package com.yoonho.holoboard.dtos;

import lombok.Data;

/**
 * packageName    : com.yoonho.holostats.dtos
 * fileName       : YoutubeChannelDto
 * author         : kim-yoonho
 * date           : 1/19/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/19/24        kim-yoonho       최초 생성
 */

@Data
public class YoutubeChannelDto {
    private String channelName;
    private String thumbNail;
    private String uploadId;
}
