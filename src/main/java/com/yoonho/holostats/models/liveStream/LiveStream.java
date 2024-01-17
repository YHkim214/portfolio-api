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

package com.yoonho.holostats.models.liveStream;

import lombok.Data;

import java.sql.Timestamp;

/**
 * packageName    : com.yoonho.holostats.models
 * fileName       : LS
 * author         : kim-yoonho
 * date           : 1/17/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/17/24        kim-yoonho       최초 생성
 */

@Data
public class LiveStream {
    private Integer lsId;           /** 라이브 스트리밍 아이디 **/
    private Integer channelId;      /** 채널 아이디 **/
    private String lsName;          /** 라이브 스트리밍 이름 **/
    private String lsYtId;          /** 라이브 스트리밍 유튜브 아이다 **/
    private Timestamp startTime;    /** 라이브 스트리밍 시작시각 **/
    private Timestamp endTime;      /** 라이브 스트리밍 종료시각 **/
    private Integer maxViewer;      /** 라이브 스트리밍 최대 시청자 **/
    private Integer avgViewer;      /** 라이브 스트리밍 평균 시정자 **/
    private Integer goodCnt;        /** 라이브 스트리밍 좋아요 수 **/
    private String lsStatus;        /** 라이브 스트리밍 상태 값 **/
    private Timestamp createTime;   /** 생성시각 **/
    private Timestamp updateTime;   /** 수정시각 **/
}
