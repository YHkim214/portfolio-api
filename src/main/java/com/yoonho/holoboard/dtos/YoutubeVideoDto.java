package com.yoonho.holoboard.dtos;

import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoLiveStreamingDetails;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatistics;
import lombok.Data;

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
    private String thumbnail;
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
        this.thumbnail = snippet.getThumbnails().getMaxres() == null ?
                snippet.getThumbnails().getDefault().getUrl() :
                snippet.getThumbnails().getMaxres().getUrl();

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
