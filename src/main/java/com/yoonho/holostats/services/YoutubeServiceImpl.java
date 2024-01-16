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
import com.google.api.services.youtube.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    @Override
    public List<Video> getVideoInfo(List<String> videoIds) throws IOException {
        /**
         * 방송 예정 와꾸 잡는 흐름
         * 1. 채널에서 upload 재생목록 id를 가져온다(최초 한번이면 될듯).
         * 2. 해당 재생목록의 맨위 동영상 5개정도를 가져온다.
         * 3. 동영상의 정보를 체크해서 upcomming 이면 디비에 등록해 준다.
         **/

        log.info("start retrieving video info for -> {}", videoIds);

        YouTube.Videos.List videosListRequest = youtube.videos()
                .list(List.of(SNIPPET, LIVE_STREAMING_DETAILS))
                .setId(videoIds)
                .setKey(apiKey);

        VideoListResponse response = videosListRequest.execute();

        log.info("start retrieving video info for -> {} complete", videoIds);

        return response.getItems();
    }

    @Override
    public List<String> getRecentVideoIds(String uploadId) throws IOException {
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
