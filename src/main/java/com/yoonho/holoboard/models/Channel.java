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

package com.yoonho.holoboard.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public class Channel {
    private Integer channelId;          //채널 아이디
    private String channelName;         //채널 명
    private String channelYtId;         //유튜브 채널 아이디
    private String channelYtName;       //유튜브 채널 명
    private String channelYtThumbnail;  //유튜브 채널 섬네일
    private String channelUploadId;     //유튜브 채널 업로드 재생목록 아이디
    private String channelStatus;       //채널 상태
    private Timestamp createTime;
    private Timestamp updateTime;
}
