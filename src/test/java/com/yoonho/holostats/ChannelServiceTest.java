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

package com.yoonho.holostats;

import com.yoonho.holostats.common.CommonCodes;
import com.yoonho.holostats.repositories.ChannelRepository;
import com.yoonho.holostats.services.channel.ChannelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * packageName    : com.yoonho.holostats
 * fileName       : ChannelServiceTest
 * author         : kim-yoonho
 * date           : 1/11/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/11/24        kim-yoonho       최초 생성
 */
@SpringBootTest
public class ChannelServiceTest {

    @Autowired
    private ChannelService channelService;

    @Autowired
    private ChannelRepository channelRepository;

    @Test
    void test() throws IOException {
//        channelService.insertChannels(channelService.getChannels());
        System.out.println(channelRepository.getChannelListByStatus(CommonCodes.CHANNEL_STATUS.ACTIVE.CODE));
    }

}
