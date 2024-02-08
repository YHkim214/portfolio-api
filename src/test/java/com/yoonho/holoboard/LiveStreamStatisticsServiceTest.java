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

package com.yoonho.holoboard;

import com.yoonho.holoboard.repositories.liveStream.LiveStreamStatisticsRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

/**
 * packageName    : com.yoonho.holostats
 * fileName       : LiveStreamStatisticsServiceTest
 * author         : kim-yoonho
 * date           : 1/18/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/18/24        kim-yoonho       최초 생성
 */
@SpringBootTest
@Slf4j
public class LiveStreamStatisticsServiceTest {

    @Autowired
    private LiveStreamStatisticsRepository liveStreamStatisticsRepository;

    @Test
    void test() {
        Optional<Integer> result = liveStreamStatisticsRepository.getAvgViewer(17);
        log.info("{}", result.get());
    }

}
