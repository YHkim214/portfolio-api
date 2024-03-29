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

import com.yoonho.holoboard.services.youtube.YoutubeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * packageName    : com.yoonho.holostats
 * fileName       : YoutubeServiceTest
 * author         : kim-yoonho
 * date           : 1/15/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/15/24        kim-yoonho       최초 생성
 */

@SpringBootTest
public class YoutubeServiceTest {

    @Autowired
    private YoutubeService youtubeService;

    @Test
    void test() throws IOException {
        System.out.println(youtubeService.getVideoInfo(youtubeService.getRecentVideoIds("UU1DCedRgGHBdm81E1llLhOQ")));
    }

}

