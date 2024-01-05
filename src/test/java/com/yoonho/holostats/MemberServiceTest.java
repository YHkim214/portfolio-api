package com.yoonho.holostats;

import com.yoonho.holostats.dtos.request.RegisterMemberRequestDto;
import com.yoonho.holostats.services.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * packageName    : com.yoonho.holostats
 * fileName       : MemberServiceTest
 * author         : kim-yoonho
 * date           : 12/21/23
 * description    : 회원 서비스 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/21/23        kim-yoonho       최초 생성
 */
@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    void getMemberTest() {
        System.out.println(memberService.getMemberByName("yoonho214"));
    }

}
