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
import java.util.*;

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
    private final String NONE = "none";

    public LiveStreamServiceImpl(YoutubeService youtubeService, ChannelRepository channelRepository, LiveStreamRepository liveStreamRepository, LiveStreamStatisticsRepository liveStreamStatisticsRepository) {
        this.youtubeService = youtubeService;
        this.channelRepository = channelRepository;
        this.liveStreamRepository = liveStreamRepository;
        this.liveStreamStatisticsRepository = liveStreamStatisticsRepository;
    }

    /** 등록된 채널의 실시간 방송 등록 **/
    @Override
    public void getLiveStreamFromYoutube() throws IOException {
        /**
         * 작업 흐름
         * 1. 현재 액티브한 채널 목록을 가져온다.
         * 2. 채널목록에서 업로드한 영상 재생목록 아이디를 꺼내 상위5개 영상아이디를 가져온다.
         * 3. 영상아이디를 검색에 정보를 가져온다.
         * 4. 정보를 분석해 라이브 스트리밍 와꾸(예정 혹은 진행중인)를 디비에 등록한다.
         **/

        List<Channel> activeChannelList = channelRepository.getChannelListByStatus(CommonCodes.CHANNEL_STATUS.ACTIVE.CODE);

        if(CollectionUtil.isNullOrEmpty(activeChannelList)) {
            log.info("there is no active channel!!");
            return;
        }

        Map<String, Integer> videoChannelIdMap = new HashMap<>();
        List<String> videoIds = new ArrayList<>();

        activeChannelList.forEach(channel -> {
            try {
                List<String> topVideoIds = youtubeService.getRecentVideoIds(channel.getChannelUploadId());

                topVideoIds.forEach(id -> videoChannelIdMap.put(id, channel.getChannelId()));

                videoIds.addAll(topVideoIds);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        List<Video> videoInfoList = youtubeService.getVideoInfo(videoIds);
        insertLiveStream(videoChannelIdMap, videoInfoList);

    }

    private void insertLiveStream(Map<String, Integer> videoChannelMap ,List<Video> videoInfoList) {
        /**
         * 라이브 스트림 등록 기준
         * 1. liveBroadcastContent = live, upcoming인 방송
         **/
        videoInfoList.forEach(video -> {
            VideoSnippet snippet = video.getSnippet();
            VideoLiveStreamingDetails videoLiveStreamingDetails = video.getLiveStreamingDetails();
            VideoStatistics videoStatistics = video.getStatistics();

            if(snippet.getLiveBroadcastContent().equals(UPCOMING) || snippet.getLiveBroadcastContent().equals(LIVE)) {
                log.info("liveStream detected! inserting info");

                LiveStream liveStream = new LiveStream();
                liveStream.setChannelId(videoChannelMap.get(video.getId()));
                liveStream.setLsName(snippet.getTitle());
                liveStream.setLsYtId(video.getId());
                liveStream.setStartTime(new Timestamp(videoLiveStreamingDetails.getScheduledStartTime().getValue()));
                liveStream.setLsStatus(
                        snippet.getLiveBroadcastContent().equals(UPCOMING) ?
                                CommonCodes.LIVE_STREAM_STATUS.UPCOMING.CODE :
                                CommonCodes.LIVE_STREAM_STATUS.LIVE.CODE
                );
                liveStream.setGoodCnt(videoStatistics.getLikeCount().intValue());

                liveStreamRepository.upsertLiveStream(liveStream);

                log.info("liveStream info inserted");
            }
        });
    }

    /** 등록된 라이브중인 라이브 스트림 업데이트 **/
    @Override
    public void updateLiveStreamStatistics() throws IOException {
        List<LiveStream> activeLiveStreamList = liveStreamRepository.getLiveStreamByStatus(CommonCodes.LIVE_STREAM_STATUS.LIVE.CODE);

        if(CollectionUtil.isNullOrEmpty(activeLiveStreamList)) {
            log.info("there is no live stream!");
            return;
        }

        List<Video> videoInfo = youtubeService.getVideoInfo(activeLiveStreamList.stream().map(liveStream -> liveStream.getLsYtId()).toList());

        videoInfo.forEach(video -> {
            VideoLiveStreamingDetails videoLiveStreamingDetails = video.getLiveStreamingDetails();
            VideoStatistics videoStatistics = video.getStatistics();
            VideoSnippet snippet = video.getSnippet();

            int concurrentViewers = videoLiveStreamingDetails.getConcurrentViewers().intValue();

            LiveStream liveStream = activeLiveStreamList.stream().filter(ls -> ls.getLsYtId().equals(video.getId())).toList().get(0);

            if(liveStream == null) return;

            /*라이브 스트림 통계자료 업데이트*/
            LiveStreamStatistics liveStreamStatistics = new LiveStreamStatistics();
            liveStreamStatistics.setLsId(liveStream.getLsId());
            liveStreamStatistics.setConcurrentViewer(concurrentViewers);

            liveStreamStatisticsRepository.insertLiveStreamStatistics(liveStreamStatistics);

            /*라이브 스트림 업데이트*/
            liveStream.setGoodCnt(videoStatistics.getLikeCount().intValue());
            if(liveStream.getMaxViewer().intValue() < concurrentViewers) {
                liveStream.setMaxViewer(concurrentViewers);
            }

            Optional<Integer> avgViewer = liveStreamStatisticsRepository.getAvgViewer(liveStream.getLsId());

            if(avgViewer.isPresent()) {
                liveStream.setAvgViewer(avgViewer.get());
            }

            if(snippet.getLiveBroadcastContent().equals(NONE)) {
                liveStream.setLsStatus(CommonCodes.LIVE_STREAM_STATUS.END.CODE);
            }

            liveStreamRepository.upsertLiveStream(liveStream);
        });
    }

    /** 진행예정 라이브 스트림 시작여부 체크 **/
    @Override
    public void checkUpcomingLiveStream() throws IOException {
        List<LiveStream> upcomingLiveStreams = liveStreamRepository.getLiveStreamByStatus(CommonCodes.LIVE_STREAM_STATUS.UPCOMING.CODE);

        if(CollectionUtil.isNullOrEmpty(upcomingLiveStreams)) {
            log.info("there is no upcoming liveStream!");
            return;
        }

        List<LiveStream> upcomingLiveStreamsTargets = upcomingLiveStreams
                .stream().filter(liveStream -> liveStream.isAboutToStart()).toList();

        if(CollectionUtil.isNullOrEmpty(upcomingLiveStreamsTargets)) {
            log.info("there is no upcoming liveStream about to start soon!!");
        }

        List<Video> videoInfoList = youtubeService.getVideoInfo(upcomingLiveStreamsTargets
                .stream().map(liveStream -> liveStream.getLsYtId()).toList());

        videoInfoList.forEach(video -> {
            VideoSnippet snippet = video.getSnippet();

            LiveStream liveStream = upcomingLiveStreamsTargets.stream().filter(ls -> ls.getLsYtId().equals(video.getId())).toList().get(0);

            if(liveStream == null) return;

            if(snippet.getLiveBroadcastContent().equals(LIVE)) {
                liveStream.setLsStatus(CommonCodes.LIVE_STREAM_STATUS.LIVE.CODE);
            }

            if(snippet.getLiveBroadcastContent().equals(NONE)) {
                liveStream.setLsStatus(CommonCodes.LIVE_STREAM_STATUS.END.CODE);
            }

            liveStreamRepository.upsertLiveStream(liveStream);
        });
    }
}
