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

package com.yoonho.holostats.scheduler;

import com.yoonho.holostats.models.Channel;
import com.yoonho.holostats.services.channel.ChannelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * packageName    : com.yoonho.holostats.scheduler
 * fileName       : ChannelScheduler
 * author         : kim-yoonho
 * date           : 1/11/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/11/24        kim-yoonho       최초 생성
 */

@Component
@Slf4j
public class ChannelScheduler {

    private final ChannelService channelService;

    public ChannelScheduler(ChannelService channelService) {
        this.channelService = channelService;
    }

    @Scheduled(initialDelay = 1000 * 5, fixedDelay=Long.MAX_VALUE)
    public void getAndInsertChannel() throws IOException {
        log.info("===================================Crawling started!");
        List<Channel> channels = channelService.getChannels();
        channelService.insertChannels(channels);
        log.info("===================================Crawling finished!");
    }
}
