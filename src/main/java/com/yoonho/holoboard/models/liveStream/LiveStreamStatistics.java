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

package com.yoonho.holoboard.models.liveStream;

import lombok.Data;

import java.sql.Timestamp;

/**
 * packageName    : com.yoonho.holostats.models.liveStream
 * fileName       : LiveStreamStatus
 * author         : kim-yoonho
 * date           : 1/17/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/17/24        kim-yoonho       최초 생성
 */
@Data
public class LiveStreamStatistics {
    private Integer lsStatisticsId;        /** 라이브 스트리밍 통계 아이디 **/
    private Integer lsId;               /** 라이브 스트리밍 아이디 **/
    private Integer concurrentViewer;   /** 현재 시청자 **/
    private Timestamp createTime;       /** 생성시각 **/
    private Timestamp updateTime;       /** 수정시각 **/
}
