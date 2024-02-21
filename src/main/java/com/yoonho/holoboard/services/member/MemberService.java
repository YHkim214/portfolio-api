package com.yoonho.holoboard.services.member;

import com.yoonho.holoboard.dtos.request.ChangeNicknameRequestDto;
import com.yoonho.holoboard.dtos.request.ChangePasswordRequestDto;
import com.yoonho.holoboard.dtos.request.RegisterMemberRequestDto;
import com.yoonho.holoboard.dtos.response.ChangeNicknameResponseDto;
import com.yoonho.holoboard.dtos.response.GetMemberInfoResponseDto;

import java.io.IOException;

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

    void registerMember(RegisterMemberRequestDto registerMemberRequestDto) throws IOException;
    GetMemberInfoResponseDto getMemberByName(String memberName);
    ChangeNicknameResponseDto changeNickname(String memberName, ChangeNicknameRequestDto changeNicknameRequestDto);
    void changePassword(String memberName, ChangePasswordRequestDto changePasswordRequestDto);

}
