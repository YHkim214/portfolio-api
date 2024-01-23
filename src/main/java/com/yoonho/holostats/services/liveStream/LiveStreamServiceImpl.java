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

import com.yoonho.holostats.common.CommonCodes;
import com.yoonho.holostats.dtos.YoutubeVideoDto;
import com.yoonho.holostats.dtos.request.GetLiveStreamRequestDto;
import com.yoonho.holostats.dtos.response.GetLiveStreamResponseDto;
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
    private final Integer MAX_RESULT = 20;

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

        List<YoutubeVideoDto> videoInfoList = youtubeService.getVideoInfo(videoIds);
        insertLiveStream(videoChannelIdMap, videoInfoList);

    }

    private void insertLiveStream(Map<String, Integer> videoChannelMap ,List<YoutubeVideoDto> videoInfoList) {
        /**
         * 라이브 스트림 등록 기준
         * 1. liveBroadcastContent = live, upcoming인 방송
         **/
        videoInfoList.forEach(video -> {
            if(video.getLiveBroadcastContent().equals(UPCOMING) || video.getLiveBroadcastContent().equals(LIVE)) {
                log.info("liveStream detected! inserting info");

                Optional<LiveStream> liveStreamDb = liveStreamRepository.getLiveStreamByYtId(video.getId());

                /** 이미 등록된경우, 등록예정 상태인 경우에만 통과 **/
                if(liveStreamDb.isPresent()
                        && (liveStreamDb.get().getLsStatus().equals(CommonCodes.LIVE_STREAM_STATUS.LIVE.CODE)
                        || liveStreamDb.get().getLsStatus().equals(CommonCodes.LIVE_STREAM_STATUS.END.CODE))) {
                    log.info("already exist in DB!");
                    return;
                }

                LiveStream liveStream = new LiveStream();
                liveStream.setChannelId(videoChannelMap.get(video.getId()));
                liveStream.setLsName(video.getTitle());
                liveStream.setLsYtId(video.getId());
                liveStream.setLsYtThumbnail(video.getThumbnail());
                liveStream.setStartTime(video.getScheduledStartTime());
                liveStream.setLsStatus(
                        video.getLiveBroadcastContent().equals(UPCOMING) ?
                                CommonCodes.LIVE_STREAM_STATUS.UPCOMING.CODE :
                                CommonCodes.LIVE_STREAM_STATUS.LIVE.CODE
                );
                liveStream.setGoodCnt(video.getGoodCnt());
                liveStream.setMaxViewer(0);
                liveStream.setAvgViewer(0);
                liveStream.setConcurrentViewer(0);

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

        List<YoutubeVideoDto> videoInfo = youtubeService.getVideoInfo(activeLiveStreamList.stream().map(liveStream -> liveStream.getLsYtId()).toList());

        videoInfo.forEach(video -> {
            LiveStream liveStream = activeLiveStreamList.stream().filter(ls -> ls.getLsYtId().equals(video.getId())).toList().get(0);

            if(liveStream == null) return;

            /*라이브 스트림 통계자료 업데이트*/
            LiveStreamStatistics liveStreamStatistics = new LiveStreamStatistics();
            liveStreamStatistics.setLsId(liveStream.getLsId());
            liveStreamStatistics.setConcurrentViewer(video.getConcurrentViewers());

            liveStreamStatisticsRepository.insertLiveStreamStatistics(liveStreamStatistics);

            /*라이브 스트림 업데이트*/
            liveStream.setGoodCnt(video.getGoodCnt());
            if(liveStream.getMaxViewer().intValue() < video.getConcurrentViewers()) {
                liveStream.setMaxViewer(video.getConcurrentViewers());
            }

            liveStream.setConcurrentViewer(video.getConcurrentViewers());

            Optional<Integer> avgViewer = liveStreamStatisticsRepository.getAvgViewer(liveStream.getLsId());

            if(avgViewer.isPresent()) {
                liveStream.setAvgViewer(avgViewer.get());
            }

            if(video.getLiveBroadcastContent().equals(NONE)) {
                liveStream.setLsStatus(CommonCodes.LIVE_STREAM_STATUS.END.CODE);
            }

            liveStreamRepository.upsertLiveStream(liveStream);
        });
    }

    /** 진행예정 라이브 스트림 상태 체크 **/
    @Override
    public void checkUpcomingLiveStream() throws IOException {
        List<LiveStream> upcomingLiveStreamList = liveStreamRepository.getLiveStreamByStatus(CommonCodes.LIVE_STREAM_STATUS.UPCOMING.CODE);

        if(CollectionUtil.isNullOrEmpty(upcomingLiveStreamList)) {
            log.info("there is no upcoming liveStream!");
            return;
        }

        List<LiveStream> upcomingLiveStreamsTargets = upcomingLiveStreamList
                .stream().filter(liveStream -> liveStream.isAboutToStart()).toList();

        if(CollectionUtil.isNullOrEmpty(upcomingLiveStreamsTargets)) {
            log.info("there is no upcoming liveStream about to start soon!!");
            return;
        }

        List<YoutubeVideoDto> videoInfoList = youtubeService.getVideoInfo(upcomingLiveStreamsTargets
                .stream().map(liveStream -> liveStream.getLsYtId()).toList());

        videoInfoList.forEach(video -> {
            LiveStream liveStream = upcomingLiveStreamsTargets.stream().filter(ls -> ls.getLsYtId().equals(video.getId())).toList().get(0);

            if(liveStream == null) return;

            if(video.getLiveBroadcastContent().equals(LIVE)) {
                liveStream.setLsStatus(CommonCodes.LIVE_STREAM_STATUS.LIVE.CODE);
            }

            if(video.getLiveBroadcastContent().equals(NONE)) {
                liveStream.setLsStatus(CommonCodes.LIVE_STREAM_STATUS.END.CODE);
            }

            liveStream.setLsName(video.getTitle());
            liveStream.setLsYtThumbnail(video.getThumbnail());

            liveStreamRepository.upsertLiveStream(liveStream);
        });
    }

    /** 일정 시간이 지난 라이브 스트림 처리 **/
    @Override
    public void cleanUpLiveStream() throws IOException {
        List<LiveStream> liveStreamList = liveStreamRepository.getLiveStreamByStatus(null);

        liveStreamList.forEach(liveStream -> {
            if(!liveStream.getLsStatus().equals(CommonCodes.LIVE_STREAM_STATUS.END.CODE) && liveStream.shouldBeOver()) {
                liveStream.setLsStatus(CommonCodes.LIVE_STREAM_STATUS.END.CODE);
                liveStreamRepository.upsertLiveStream(liveStream);
            }
        });

        /** 라이브 스트림이 종료되었지만, 종료시각이 업데이트 되지 않은 경우 **/
        List<LiveStream> endedLiveStream = liveStreamList.stream()
                .filter(liveStream -> liveStream.getEndTime() == null && liveStream.getLsStatus().equals(CommonCodes.LIVE_STREAM_STATUS.END.CODE))
                .toList();

        if(!endedLiveStream.isEmpty()) {
            List<YoutubeVideoDto> videoInfoList = youtubeService
                    .getVideoInfo(endedLiveStream.stream().map(liveStream -> liveStream.getLsYtId()).toList());

            videoInfoList.forEach(video -> {
                LiveStream liveStream = endedLiveStream.stream().filter(ls -> ls.getLsYtId().equals(video.getId())).toList().get(0);

                liveStream.setEndTime(video.getActualEndTime());

                liveStreamRepository.upsertLiveStream(liveStream);
            });
        }
    }

    @Override
    public List<GetLiveStreamResponseDto> getLiveStream(GetLiveStreamRequestDto getLiveStreamRequestDto) {
        return liveStreamRepository
                .getLiveStream(MAX_RESULT, getLiveStreamRequestDto.getDate())
                .stream().map(liveStream -> new GetLiveStreamResponseDto(liveStream))
                .toList();
    }
}
