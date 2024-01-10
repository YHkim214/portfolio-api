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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yoonho.holostats.configs.security.JwtGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * packageName    : com.yoonho.holostats
 * fileName       : JwtGeneratorTest
 * author         : kim-yoonho
 * date           : 1/10/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/10/24        kim-yoonho       최초 생성
 */

@SpringBootTest
public class JwtGeneratorTest {

    @Autowired
    private JwtGenerator jwtGenerator;

    @Test
    void test() throws JsonProcessingException {
//        jwtGenerator.validateRefreshToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ5b29uaG8yMTQiLCJpYXQiOjE3MDQ4NDg2NTIsImV4cCI6MTcwNDg1MDQ1Mn0.4Zvrr1aFTFi2TA42xpqdk6jTqWtvQJ-dfFlzw5nXgeNeoH_XNpB8tYfaCatvTu4qif_i3jUc9hpkKYIX2j-pxA");
    }

}
