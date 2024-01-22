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

package com.yoonho.holostats.services.liveStream;

import com.yoonho.holostats.dtos.request.GetLiveStreamRequestDto;
import com.yoonho.holostats.models.liveStream.LiveStream;

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
    List<LiveStream> getLiveStream(GetLiveStreamRequestDto getLiveStreamRequestDto);
}
