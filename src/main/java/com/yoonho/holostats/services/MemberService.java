package com.yoonho.holostats.services;

import com.yoonho.holostats.dtos.RegisterMemberDto;

/**
 * packageName    : com.yoonho.holostats.services
 * fileName       : MemberService
 * author         : kim-yoonho
 * date           : 12/21/23
 * description    : 회원 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/21/23        kim-yoonho       최초 생성
 */
public interface MemberService {

    void insertMember(RegisterMemberDto registerMemberDto);

}
