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

import java.sql.Time;
import java.sql.Timestamp;

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
    private String channelYtId;     //유튜브 채널 아이디
    private String channelUploadId; //유튜브 채널 업로드 재생목록 아이디
    private String channelStatus;   //채널 상태
    private Timestamp createTime;
    private Timestamp updateTime;

    public Channel(String channelName, String channelYtId, String channelUploadId, String channelStatus) {
        this.channelName = channelName;
        this.channelYtId = channelYtId;
        this.channelUploadId = channelUploadId;
        this.channelStatus = channelStatus;
    }
}
