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

package com.yoonho.holostats.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * packageName    : com.yoonho.holostats.models
 * fileName       : Channel
 * author         : kim-yoonho
 * date           : 1/11/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/11/24        kim-yoonho       최초 생성
 */
@Data
@AllArgsConstructor
public class Channel {
    private Integer channelId;      //채널 아이디
    private String channelName;     //채널 명
    private String channelYtId;            //유튜브 채널 아이디
    private String channelStatus;   //채널 상태

    public Channel(String channelName, String channelYtId, String channelStatus) {
        this.channelName = channelName;
        this.channelYtId = channelYtId;
        this.channelStatus = channelStatus;
    }
}
