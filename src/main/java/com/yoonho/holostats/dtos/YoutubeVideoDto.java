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

import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.model.*;

import jdk.jshell.Snippet;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * packageName    : com.yoonho.holostats.dtos
 * fileName       : YoutubeDto
 * author         : kim-yoonho
 * date           : 1/19/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/19/24        kim-yoonho       최초 생성
 */

@Data
public class YoutubeVideoDto {

    private String id;
    private String title;
    private String liveBroadcastContent;
    private Timestamp scheduledStartTime;
    private Timestamp actualEndTime;
    private int concurrentViewers;
    private int goodCnt;

    public YoutubeVideoDto(Video video) {
        VideoSnippet snippet = video.getSnippet();
        VideoLiveStreamingDetails liveBroadcastContentDetails = video.getLiveStreamingDetails();
        VideoStatistics videoStatistics = video.getStatistics();

        this.id = video.getId();
        this.title = snippet.getTitle();
        this.liveBroadcastContent = snippet.getLiveBroadcastContent();

        if(liveBroadcastContentDetails != null) {
            this.scheduledStartTime = liveBroadcastContentDetails.getScheduledStartTime() == null ?
                    null : new Timestamp(liveBroadcastContentDetails.getScheduledStartTime().getValue());
            this.actualEndTime = liveBroadcastContentDetails.getActualEndTime() == null
                    ? null : new Timestamp(liveBroadcastContentDetails.getActualEndTime().getValue());
            this.concurrentViewers = liveBroadcastContentDetails.getConcurrentViewers() == null
                    ? 0 : liveBroadcastContentDetails.getConcurrentViewers().intValue();
        }

        if(videoStatistics != null) {
            this.goodCnt = videoStatistics.getLikeCount() == null ? 0 : videoStatistics.getLikeCount().intValue();
        }
    }
}
