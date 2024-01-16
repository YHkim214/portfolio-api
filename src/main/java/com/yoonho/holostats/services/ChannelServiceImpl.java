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

package com.yoonho.holostats.services;

import com.yoonho.holostats.common.CommonCodes;
import com.yoonho.holostats.models.Channel;
import com.yoonho.holostats.models.CommonCode;
import com.yoonho.holostats.repositories.ChannelRepository;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * packageName    : com.yoonho.holostats.services
 * fileName       : ChannelServiceImpl
 * author         : kim-yoonho
 * date           : 1/11/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/11/24        kim-yoonho       최초 생성
 */

@Service
@Slf4j
public class ChannelServiceImpl implements ChannelService{

    @Value("${crawler.base}")
    private String crawlerBase;

    private final ChannelRepository channelRepository;

    private final YoutubeService youtubeService;

    public ChannelServiceImpl(ChannelRepository channelRepository, YoutubeService youtubeService) {
        this.channelRepository = channelRepository;
        this.youtubeService = youtubeService;
    }

    public List<Channel> getChannels() throws IOException {
        Document document = Jsoup.connect(crawlerBase).get();
        Elements talentElements = document.getElementsByClass("talent_list").get(0).select("li");

        List<Channel> talentList = talentElements.stream().map(talent -> {
            talent.select("h3 span").remove();
            String channelName = talent.select("h3").text();
            String ytId = "";
            String uploadId = "";

            log.info("crawling start for => {}", channelName);
            try {
                ytId = getYtId(talent).orElseThrow();
                uploadId = youtubeService.getUploadId(ytId).orElseThrow();
            } catch (Exception e) {
                log.info("retreiving ytId for {} failed", channelName);
            }

            log.info("crawling for {} finished", channelName);

            return new Channel(channelName, ytId, uploadId, CommonCodes.CHANNEL_STATUS.CHANNEL_STATUS_ACTIVE.CODE);

        }).collect(Collectors.toList());

        return talentList;
    }

    public void insertChannels(List<Channel> channels) {
        channelRepository.upsertChannels(channels);
    }

    public Optional<String> getYtId(Element element) throws IOException, InterruptedException {
        //크롤링을 위한 딜레이
        Thread.sleep(1000);
        String url = element.select("a").attr("href");
        String ytUrl= Jsoup.connect(url)
                .get()
                .getElementsByClass("t_sns")
                .get(0)
                .selectFirst("li")
                .selectFirst("a")
                .attr("href");

        String ytId = ytUrl.substring(ytUrl.lastIndexOf("/") + 1, ytUrl.lastIndexOf("?") > 0 ? ytUrl.lastIndexOf("?") : ytUrl.length());

        return Optional.ofNullable(ytId);
    }
}
