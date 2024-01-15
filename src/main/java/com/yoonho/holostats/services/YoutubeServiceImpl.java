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

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ActivityListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * packageName    : com.yoonho.holostats.services
 * fileName       : YoutubeServiceImpl
 * author         : kim-yoonho
 * date           : 1/15/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/15/24        kim-yoonho       최초 생성
 */

@Service
@Slf4j
public class YoutubeServiceImpl implements YoutubeService{

    private final YouTube youtube;

    @Value("${youtube.api.key}")
    private String apiKey;

    private List<String> SEARCH_PART = List.of("contentDetails");

    private final long MAX_RESULT = 5L;

    public YoutubeServiceImpl(YouTube youtube) {
        this.youtube = youtube;
    }

    @Override
    public void getActivityList(String channelId) throws IOException {
        YouTube.Activities.List request = youtube.activities()
                .list(SEARCH_PART);
        request.setKey(apiKey)
                .setMaxResults(MAX_RESULT)
                .setChannelId(channelId);
        ActivityListResponse response = request.execute();
        System.out.println(response);
    }
}
