package com.yoonho.holoboard.services.liveStream;

import com.yoonho.holoboard.dtos.request.GetLiveStreamRequestDto;
import com.yoonho.holoboard.dtos.response.GetLiveStreamResponseDto;

import java.io.IOException;
import java.util.List;

/**
 * packageName    : com.yoonho.holostats.services.ls
 * fileName       : LSService
 * author         : kim-yoonho
 * date           : 1/17/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/17/24        kim-yoonho       최초 생성
 */
public interface LiveStreamService {
    void getLiveStreamFromYoutube() throws IOException;
    void updateLiveStreamStatistics() throws IOException;
    void checkUpcomingLiveStream() throws IOException;
    void cleanUpLiveStream() throws IOException;
    List<GetLiveStreamResponseDto> getLiveStreamList(GetLiveStreamRequestDto getLiveStreamRequestDto);
    GetLiveStreamResponseDto getLiveStreamById(Integer lsId);
}
