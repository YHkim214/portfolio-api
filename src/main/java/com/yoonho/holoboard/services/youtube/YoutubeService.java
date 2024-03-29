package com.yoonho.holoboard.services.youtube;

import com.yoonho.holoboard.dtos.YoutubeChannelDto;
import com.yoonho.holoboard.dtos.YoutubeVideoDto;

import java.io.IOException;
import java.util.List;

/**
 * packageName    : com.yoonho.holostats.services
 * fileName       : YoutubeService
 * author         : kim-yoonho
 * date           : 1/15/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/15/24        kim-yoonho       최초 생성
 */
public interface YoutubeService {

    List<YoutubeVideoDto> getVideoInfo(List<String> videoIds) throws IOException;

    List<String> getRecentVideoIds(String uploadId) throws IOException;

    YoutubeChannelDto getChannelInfo(String channelId) throws IOException;

}
