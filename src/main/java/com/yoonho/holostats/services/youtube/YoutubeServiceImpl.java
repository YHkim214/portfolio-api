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
import com.yoonho.holostats.dtos.YoutubeVideoDto;
import com.yoonho.holostats.utils.CollectionUtil;
import com.yoonho.holostats.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
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

    public YoutubeServiceImpl(YouTube youtube) {
        this.youtube = youtube;
    }

    @Value("${youtube.api.key}")
    private String apiKey;
    private final String SNIPPET = "snippet";
    private final String CONTENT_DETAILS = "contentDetails";
    private final String LIVE_STREAMING_DETAILS = "liveStreamingDetails";
    private final String STATISTICS = "statistics";
    private final Integer MAX_ID_COUNT = 50;
    private final long MAX_RESULT = 5L;



    /**
     * 비디오 정보 가져오기
     **/
    @Override
    public List<YoutubeVideoDto> getVideoInfo(List<String> videoIds) throws IOException {

        if(CollectionUtil.isNullOrEmpty(videoIds)) {
            log.info("there is no videoId!!!");
            return new ArrayList<>();
        }

        log.info("start retrieving {} video info", videoIds.size());

        int startIndex = -1;
        int endIndex = 0;

        List<Video> videoInfoList = new ArrayList<>();

        while (endIndex < videoIds.size()){

            if(startIndex == -1) {
                startIndex = 0;
                endIndex = MAX_ID_COUNT;
            } else {
                startIndex += MAX_ID_COUNT;
                endIndex += MAX_ID_COUNT;
            }

            YouTube.Videos.List videosListRequest = youtube.videos()
                    .list(List.of(SNIPPET, LIVE_STREAMING_DETAILS, STATISTICS))
                    .setId(videoIds.subList(startIndex, endIndex <= videoIds.size() ? endIndex : videoIds.size() ))
                    .setKey(apiKey);

            VideoListResponse response = videosListRequest.execute();

            videoInfoList.addAll(response.getItems());
        }

        log.info("start retrieving video complete", videoIds);

        return videoInfoList.stream().map(video -> new YoutubeVideoDto(video)).toList();
    }

    /** 재생목록의 비디오 목록 가져오기 **/
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

    /** 업로드 재생목록의 아이디 가져오기 **/
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

        log.info("start retrieving channel for -> {} complete!", channelId);

        return uploadId;
    }
}
