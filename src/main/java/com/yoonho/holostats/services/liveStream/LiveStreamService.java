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

import java.io.IOException;

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
}
