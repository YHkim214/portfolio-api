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

package com.yoonho.holostats.services.liveStream;

import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoLiveStreamingDetails;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatistics;
import com.yoonho.holostats.common.CommonCodes;
import com.yoonho.holostats.models.Channel;
import com.yoonho.holostats.models.liveStream.LiveStream;
import com.yoonho.holostats.models.liveStream.LiveStreamStatistics;
import com.yoonho.holostats.repositories.ChannelRepository;
import com.yoonho.holostats.repositories.liveStream.LiveStreamRepository;
import com.yoonho.holostats.repositories.liveStream.LiveStreamStatisticsRepository;
import com.yoonho.holostats.services.youtube.YoutubeService;
import com.yoonho.holostats.utils.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

/**
 * packageName    : com.yoonho.holostats.services.ls
 * fileName       : LSServiceImpl
 * author         : kim-yoonho
 * date           : 1/17/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/17/24        kim-yoonho       최초 생성
 */

@Service
@Slf4j
public class LiveStreamServiceImpl implements LiveStreamService {

    private final YoutubeService youtubeService;
    private final ChannelRepository channelRepository;

    private final LiveStreamRepository liveStreamRepository;

    private final LiveStreamStatisticsRepository liveStreamStatisticsRepository;

    private final String UPCOMING = "upcoming";
    private final String LIVE = "live";

    public LiveStreamServiceImpl(YoutubeService youtubeService, ChannelRepository channelRepository, LiveStreamRepository liveStreamRepository, LiveStreamStatisticsRepository liveStreamStatisticsRepository) {
        this.youtubeService = youtubeService;
        this.channelRepository = channelRepository;
        this.liveStreamRepository = liveStreamRepository;
        this.liveStreamStatisticsRepository = liveStreamStatisticsRepository;
    }

    @Override
    public void getLiveStreamFromYoutube() {
        /**
         * 작업 흐름
         * 1. 현재 액티브한 채널 목록을 가져온다.
         * 2. 채널목록에서 업로드한 영상 재생목록 아이디를 꺼내 상위5개 영상아이디를 가져온다.
         * 3. 영상아이디를 검색에 정보를 가져온다.
         * 4. 정보를 분석해 라이브 스트리밍 와꾸(예정 혹은 진행중인)를 디비에 등록한다.
         **/

        List<Channel> activeChannelList = channelRepository.getChannelListByStatus(CommonCodes.CHANNEL_STATUS.ACTIVE.CODE);

        if(CollectionUtil.isNullOrEmpty(activeChannelList)) return;

        activeChannelList.forEach(channel -> {
            try {
                List<String> topVideoIds = youtubeService.getRecentVideoIds(channel.getChannelUploadId());
                List<Video> topVideoInfo = youtubeService.getVideoInfo(topVideoIds);

                insertLiveStream(channel, topVideoInfo);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void insertLiveStream(Channel channel, List<Video> topVideoInfo) {
        /**
         * 라이브 스트림 등록 기준
         * 1. liveBroadcastContent = live, upcoming인 방송
         **/

        topVideoInfo.forEach(video -> {
            VideoSnippet snippet = video.getSnippet();
            VideoLiveStreamingDetails videoLiveStreamingDetails = video.getLiveStreamingDetails();
            VideoStatistics videoStatistics = video.getStatistics();

            if(snippet.getLiveBroadcastContent().equals(UPCOMING) || snippet.getLiveBroadcastContent().equals(LIVE)) {
                log.info("liveStream detected! inserting info");

                LiveStream liveStream = new LiveStream();
                liveStream.setChannelId(channel.getChannelId());
                liveStream.setLsName(snippet.getTitle());
                liveStream.setLsYtId(video.getId());
                liveStream.setStartTime(new Timestamp(videoLiveStreamingDetails.getScheduledStartTime().getValue()));
                liveStream.setLsStatus(
                        snippet.getLiveBroadcastContent().equals(UPCOMING) ?
                                CommonCodes.LIVE_STREAM_STATUS.UPCOMING.CODE :
                                CommonCodes.LIVE_STREAM_STATUS.LIVE.CODE
                );
                liveStream.setGoodCnt(videoStatistics.getLikeCount().intValue());

                liveStreamRepository.insertLiveStream(liveStream);

                log.info("liveStream info inserted");
            }
        });
    }

    @Override
    public void updateLiveStreamFromYoutube() throws IOException {
        List<LiveStream> activeLiveStreamList = liveStreamRepository.getLiveStreamByStatus(CommonCodes.LIVE_STREAM_STATUS.LIVE.CODE);

        if(CollectionUtil.isNullOrEmpty(activeLiveStreamList)) return;

        List<Video> videoInfo = youtubeService.getVideoInfo(activeLiveStreamList.stream().map(liveStream -> liveStream.getLsYtId()).toList());

        videoInfo.forEach(video -> {
            VideoLiveStreamingDetails videoLiveStreamingDetails = video.getLiveStreamingDetails();

            LiveStream liveStream = activeLiveStreamList.stream().filter(ls -> ls.getLsYtId().equals(video.getId())).toList().get(0);

            if(liveStream == null) return;

            LiveStreamStatistics liveStreamStatistics = new LiveStreamStatistics();
            liveStreamStatistics.setLsId(liveStream.getLsId());
            liveStreamStatistics.setConcurrentViewer(videoLiveStreamingDetails.getConcurrentViewers().intValue());

            liveStreamStatisticsRepository.insertLiveStreamStatistics(liveStreamStatistics);

        });
    }

}
