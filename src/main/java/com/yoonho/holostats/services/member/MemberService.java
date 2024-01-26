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

package com.yoonho.holostats.services.member;

import com.yoonho.holostats.dtos.request.ChangeNicknameRequestDto;
import com.yoonho.holostats.dtos.request.ChangePasswordDto;
import com.yoonho.holostats.dtos.request.RegisterMemberRequestDto;
import com.yoonho.holostats.dtos.response.ChangeNicknameResponseDto;
import com.yoonho.holostats.dtos.response.GetMemberInfoResponseDto;

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
    void changePassword(String memberName, ChangePasswordDto changePasswordDto);

}
