package com.yoonho.holoboard.scheduler;

import com.yoonho.holoboard.models.Channel;
import com.yoonho.holoboard.services.channel.ChannelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

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

//@Component
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
