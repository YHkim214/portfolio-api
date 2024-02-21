package com.yoonho.holoboard.scheduler;

import com.yoonho.holoboard.services.liveStream.LiveStreamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

/**
 * packageName    : com.yoonho.holostats.scheduler
 * fileName       : LiveStreamScheduler
 * author         : kim-yoonho
 * date           : 1/19/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/19/24        kim-yoonho       최초 생성
 */

//@Component
@Slf4j
public class LiveStreamScheduler {

    private final LiveStreamService liveStreamService;

    public LiveStreamScheduler(LiveStreamService liveStreamService) {
        this.liveStreamService = liveStreamService;
    }

    /**
     * 배치 실행 주기(예상 하루 할당량)
     * 1. 실시간 방송 체크: 30분(920)
     * 2. 방송예정 와꾸체크: 1분(최대 1320)
     * 3. 실시간 방송 업데이트: 1분(최대 1320)
     * 4. 실시간 방송 정리: 1분(최대 1320)
     **/

    @Scheduled(initialDelay = 1000 * 10, fixedDelay= 1000 * 60 * 30)
    public void getLiveStreamFromYoutube() throws IOException {
        log.info("=========================getLiveStreamFromYoutube start!!=========================");
        liveStreamService.getLiveStreamFromYoutube();
        log.info("=========================getLiveStreamFromYoutube end!!=========================");
    }

    @Scheduled(initialDelay = 1000 * 60, fixedDelay= 1000 * 60)
    public void checkUpcomingLiveStream() throws IOException {
        log.info("=========================checkUpcomingLiveStream start!!=========================");
        liveStreamService.checkUpcomingLiveStream();
        log.info("=========================checkUpcomingLiveStream end!!=========================");
    }

    @Scheduled(initialDelay = 1000 * 70, fixedDelay= 1000 * 60)
    public void updateLiveStreamStatistics() throws IOException {
        log.info("=========================updateLiveStreamStatistics start!!=========================");
        liveStreamService.updateLiveStreamStatistics();
        log.info("=========================updateLiveStreamStatistics end!!=========================");
    }

    @Scheduled(initialDelay = 1000 * 80, fixedDelay= 1000 * 60)
    public void cleanUpLiveStream() throws IOException {
        log.info("=========================cleanUpLiveStream start!!=========================");
        liveStreamService.cleanUpLiveStream();
        log.info("=========================cleanUpLiveStream end!!=========================");
    }

}
