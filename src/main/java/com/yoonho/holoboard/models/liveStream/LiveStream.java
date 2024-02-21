package com.yoonho.holoboard.models.liveStream;

import com.yoonho.holoboard.models.Channel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
public class LiveStream {
    private Integer lsId;           /** 라이브 스트리밍 아이디 **/
    private Integer channelId;      /** 채널 아이디 **/
    private String lsName;          /** 라이브 스트리밍 이름 **/
    private String lsYtId;          /** 라이브 스트리밍 유튜브 아이디 **/
    private String lsYtThumbnail;   /** 라이브 스트리밍 유튜브 섬네일 **/
    private Timestamp startTime;    /** 라이브 스트리밍 시작시각 **/
    private Timestamp endTime;      /** 라이브 스트리밍 종료시각 **/
    private Integer maxViewer;      /** 라이브 스트리밍 최대 시청자 **/
    private Integer avgViewer;      /** 라이브 스트리밍 평균 시정자 **/
    private Integer concurrentViewer;   /** 라이브 스트리밍 현재 시청자 **/
    private Integer goodCnt;        /** 라이브 스트리밍 좋아요 수 **/
    private String lsStatus;        /** 라이브 스트리밍 상태 값 **/
    private Timestamp createTime;   /** 생성시각 **/
    private Timestamp updateTime;   /** 수정시각 **/

    private Channel channel;

    private final long ONE_MINUTE = 1000 * 60;
    private final long THIRTEEN_HOURS = 1000 * 60 * 60 * 13;

    public boolean isAboutToStart() {
        Timestamp curTimestamp = new Timestamp(System.currentTimeMillis() + ONE_MINUTE);
        return curTimestamp.after(this.startTime);
    }

    public boolean shouldBeOver() {
        Timestamp newTime = new Timestamp(this.startTime.getTime() + THIRTEEN_HOURS);
        Timestamp curTimestamp = new Timestamp(System.currentTimeMillis());

        return curTimestamp.after(newTime);
    }
}
