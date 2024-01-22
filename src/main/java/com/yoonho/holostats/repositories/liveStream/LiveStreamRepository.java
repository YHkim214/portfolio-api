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

package com.yoonho.holostats.repositories.liveStream;

import com.yoonho.holostats.models.liveStream.LiveStream;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.yoonho.holostats.repositories
 * fileName       : LiveStreamRepository
 * author         : kim-yoonho
 * date           : 1/17/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/17/24        kim-yoonho       최초 생성
 */
@Mapper
public interface LiveStreamRepository {
    void upsertLiveStream(@Param("liveStream") LiveStream liveStream);
    List<LiveStream> getLiveStreamByStatus(@Param("lsStatus") String lsStatus);
    Optional<LiveStream> getLiveStreamByYtId(@Param("lsYtId") String lsYtId);
    List<LiveStream> getLiveStream(@Param("maxResult") Integer maxResult, @Param("date") String date);
}
