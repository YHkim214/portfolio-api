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

package com.yoonho.holoboard.dtos.response;

import com.yoonho.holoboard.models.liveStream.LiveStream;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * packageName    : com.yoonho.holostats.dtos.response
 * fileName       : GetLiveStreamResponseDto
 * author         : kim-yoonho
 * date           : 1/23/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/23/24        kim-yoonho       최초 생성
 */
@Data
public class GetLiveStreamResponseDto {
    private Integer lsId;
    private String lsName;
    private String lsYtId;
    private String lsYtThumbnail;
    private String startTime;
    private String endTime;
    private String lsStatus;
    private Integer maxViewer;
    private Integer avgViewer;
    private Integer concurrentViewer;
    private Integer goodCnt;

    private Integer channelId;
    private String channelName;
    private String channelYtId;
    private String channelYtName;
    private String channelYtThumbnail;

    public GetLiveStreamResponseDto(LiveStream liveStream) {
        this.lsId = liveStream.getLsId();
        this.lsName = liveStream.getLsName();
        this.lsYtId = liveStream.getLsYtId();
        this.lsYtThumbnail = liveStream.getLsYtThumbnail();
        this.startTime = LocalDateTime
                .ofInstant(Instant.ofEpochMilli(liveStream.getStartTime().getTime()), TimeZone.getDefault().toZoneId())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.endTime = liveStream.getEndTime() == null ? "" : LocalDateTime
                .ofInstant(Instant.ofEpochMilli(liveStream.getEndTime().getTime()), TimeZone.getDefault().toZoneId())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.lsStatus = liveStream.getLsStatus();
        this.maxViewer = liveStream.getMaxViewer();
        this.avgViewer = liveStream.getAvgViewer();
        this.concurrentViewer = liveStream.getConcurrentViewer();
        this.goodCnt = liveStream.getGoodCnt();

        if(liveStream.getChannel() != null) {
            this.channelId = liveStream.getChannel().getChannelId();
            this.channelName = liveStream.getChannel().getChannelName();
            this.channelYtId = liveStream.getChannel().getChannelYtId();
            this.channelYtName = liveStream.getChannel().getChannelYtName();
            this.channelYtThumbnail = liveStream.getChannel().getChannelYtThumbnail();
        }
    }
}
