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

package com.yoonho.holostats.dtos;

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
