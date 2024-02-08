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

package com.yoonho.holoboard.repositories.liveStream;

import com.yoonho.holoboard.models.liveStream.LiveStreamStatistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * packageName    : com.yoonho.holostats.repositories.liveStream
 * fileName       : LiveStreamStatisticsRepository
 * author         : kim-yoonho
 * date           : 1/17/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/17/24        kim-yoonho       최초 생성
 */
@Mapper
public interface LiveStreamStatisticsRepository {
    void insertLiveStreamStatistics(@Param("liveStreamStatistics") LiveStreamStatistics liveStreamStatistics);
    Optional<Integer> getAvgViewer(@Param("lsId") Integer lsId);
}
