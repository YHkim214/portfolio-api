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

package com.yoonho.holostats.services.youtube;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import com.yoonho.holostats.utils.CollectionUtil;
import com.yoonho.holostats.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private final long MAX_RESULT = 5L;

    public YoutubeServiceImpl(YouTube youtube) {
        this.youtube = youtube;
    }

    private final String SNIPPET = "snippet";
    private final String CONTENT_DETAILS = "contentDetails";
    private final String LIVE_STREAMING_DETAILS = "liveStreamingDetails";

    private final String STATISTICS = "statistics";



    @Override
    public List<Video> getVideoInfo(List<String> videoIds) throws IOException {

        if(CollectionUtil.isNullOrEmpty(videoIds)) return new ArrayList<>();

        log.info("start retrieving video info for -> {}", videoIds);

        YouTube.Videos.List videosListRequest = youtube.videos()
                .list(List.of(SNIPPET, LIVE_STREAMING_DETAILS, STATISTICS))
                .setId(videoIds)
                .setKey(apiKey);

        VideoListResponse response = videosListRequest.execute();

        log.info("start retrieving video info for -> {} complete", videoIds);

        return response.getItems();
    }

    @Override
    public List<String> getRecentVideoIds(String uploadId) throws IOException {
        if(StringUtil.isNullOrEmpty(uploadId)) return new ArrayList<>();

        log.info("start retrieving recent videoIds for -> {}", uploadId);

        YouTube.PlaylistItems.List playListItemsRequest = youtube.playlistItems()
                .list(List.of(CONTENT_DETAILS))
                .setPlaylistId(uploadId)
                .setMaxResults(MAX_RESULT)
                .setKey(apiKey);

        PlaylistItemListResponse response = playListItemsRequest.execute();

        List<String> resultList = response.getItems().stream().map(playlistItem ->
            playlistItem.getContentDetails().getVideoId()
        ).toList();

        log.info("start retrieving recent videoIds for -> {} complete!", uploadId);
        return resultList;
    }

    @Override
    public Optional<String> getUploadId(String channelId) throws IOException {

        if(StringUtil.isNullOrEmpty(channelId)) return Optional.ofNullable(null);

        log.info("start retrieving channel for -> {}", channelId);

        YouTube.Channels.List channelsRequest = youtube.channels()
                .list(List.of(CONTENT_DETAILS))
                .setId(List.of(channelId))
                .setKey(apiKey);
        ChannelListResponse response = channelsRequest.execute();
        Optional<String> uploadId = Optional.ofNullable(response.getItems().get(0).getContentDetails().getRelatedPlaylists().getUploads());

        log.info("start retrieving channel for -> {} complete! uploadId is {}", channelId, uploadId.get());

        return uploadId;
    }
}
