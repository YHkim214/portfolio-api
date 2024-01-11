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

package com.yoonho.holostats.repositories;

import com.yoonho.holostats.models.Channel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * packageName    : com.yoonho.holostats.repositories
 * fileName       : ChannelRepository
 * author         : kim-yoonho
 * date           : 1/11/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/11/24        kim-yoonho       최초 생성
 */
@Mapper
public interface ChannelRepository {
    void upsertChannels(@Param("channels") List<Channel> channels);
}
