package com.yoonho.holoboard;

import com.yoonho.holoboard.utils.CommonCodeGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * packageName    : com.yoonho.holostats
 * fileName       : CommonCodeGeneratorTest
 * author         : kim-yoonho
 * date           : 12/21/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/21/23        kim-yoonho       최초 생성
 */
@SpringBootTest
public class CommonCodeGeneratorTest {

    private final CommonCodeGenerator commonCodeGenerator;

    @Autowired
    public CommonCodeGeneratorTest(CommonCodeGenerator commonCodeGenerator) {
        this.commonCodeGenerator = commonCodeGenerator;
    }

    @Test
    void contextLoads() {
        System.out.println(commonCodeGenerator.getCommonCodeClassString());
    }

}
