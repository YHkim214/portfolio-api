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

import com.yoonho.holostats.dtos.response.GetMemberInfoResponseDto;
import com.yoonho.holostats.exceptions.ApiException;
import com.yoonho.holostats.common.CommonCodes;
import com.yoonho.holostats.dtos.request.RegisterMemberRequestDto;
import com.yoonho.holostats.models.DbFile;
import com.yoonho.holostats.models.Member;
import com.yoonho.holostats.repositories.MemberRepository;
import com.yoonho.holostats.services.file.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

/**
 * packageName    : com.yoonho.holostats.services
 * fileName       : MemberServiceImpl
 * author         : kim-yoonho
 * date           : 12/21/23
 * description    : 회원 서비스 구현체
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/21/23        kim-yoonho       최초 생성
 */
@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final FileService fileService;

    private final PasswordEncoder passwordEncoder;

    public MemberServiceImpl(MemberRepository memberRepository,
                             FileService fileService,
                             PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.fileService = fileService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void registerMember(RegisterMemberRequestDto registerMemberRequestDto) throws IOException {
        //중복체크
        Optional<Member> memberDb = memberRepository.getMemberByName(registerMemberRequestDto.getMemberName());

        if(memberDb.isPresent()) {
            throw new ApiException(
                    Integer.parseInt(CommonCodes.ERROR_CODE.DUP_MEMBER.VAL)
                    , CommonCodes.ERROR_CODE.DUP_MEMBER.DESC);
        }

        Member member = new Member();

        member.setMemberName(registerMemberRequestDto.getMemberName());
        member.setMemberPassword(passwordEncoder.encode(registerMemberRequestDto.getMemberPassword()));
        member.setMemberNickName(registerMemberRequestDto.getMemberNickName());

        member.setMemberRole(CommonCodes.MEMBER_ROLE.ROLE_USER.CODE);
        member.setMemberStatus(CommonCodes.MEMBER_STATUS.REGISTERED.CODE);

        memberRepository.insertMember(member);

        if(registerMemberRequestDto.getMemberThumbnailFile() != null) {
            DbFile dbFile = fileService.registerFile(
                    registerMemberRequestDto.getMemberThumbnailFile(),
                    member.getMemberId(),
                    CommonCodes.FILE_TYPE.THUMBNAIL.CODE
            );

            member.setMemberThumbnailId(dbFile.getFileId());
            memberRepository.updateMember(member);
        }

    }

    @Override
    public GetMemberInfoResponseDto getMemberByName(String memberName) {
        Optional<Member> member = memberRepository.getMemberByName(memberName);

        if(member.isPresent()) {
            GetMemberInfoResponseDto getMemberInfoDto = new GetMemberInfoResponseDto();
            getMemberInfoDto.setMemberId(member.get().getMemberId());
            getMemberInfoDto.setMemberName(member.get().getMemberName());
            getMemberInfoDto.setMemberNickName(member.get().getMemberNickName());
            getMemberInfoDto.setMemberThumbnailFileUrl(member.get().getMemberThumbnailFile().getFileUrl());

            return getMemberInfoDto;
        }

        return null;
    }
}
