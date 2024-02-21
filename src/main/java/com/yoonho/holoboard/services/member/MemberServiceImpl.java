package com.yoonho.holoboard.services.member;

import com.yoonho.holoboard.common.CommonCodes;
import com.yoonho.holoboard.dtos.request.ChangeNicknameRequestDto;
import com.yoonho.holoboard.dtos.request.ChangePasswordRequestDto;
import com.yoonho.holoboard.dtos.request.RegisterMemberRequestDto;
import com.yoonho.holoboard.dtos.response.ChangeNicknameResponseDto;
import com.yoonho.holoboard.dtos.response.GetMemberInfoResponseDto;
import com.yoonho.holoboard.exceptions.ApiException;
import com.yoonho.holoboard.models.DbFile;
import com.yoonho.holoboard.models.Member;
import com.yoonho.holoboard.repositories.MemberRepository;
import com.yoonho.holoboard.services.file.FileService;
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
        Member member = memberRepository.getMemberByName(memberName)
                .orElseThrow(() -> new ApiException(999, "회원정보가 존재하지 않습니다."));

        GetMemberInfoResponseDto getMemberInfoDto = new GetMemberInfoResponseDto();
        getMemberInfoDto.setMemberId(member.getMemberId());
        getMemberInfoDto.setMemberName(member.getMemberName());
        getMemberInfoDto.setMemberNickName(member.getMemberNickName());
        getMemberInfoDto.setMemberThumbnailFileUrl(member.getMemberThumbnailFile().getFileUrl());

        return getMemberInfoDto;
    }

    @Override
    public ChangeNicknameResponseDto changeNickname(String memberName, ChangeNicknameRequestDto changeNicknameRequestDto) {
        Member member = memberRepository.getMemberByName(memberName)
                .orElseThrow(() -> new ApiException(999, "회원정보가 존재하지 않습니다."));

        member.setMemberNickName(changeNicknameRequestDto.getNewNickname());

        memberRepository.updateMember(member);

        return new ChangeNicknameResponseDto(changeNicknameRequestDto.getNewNickname());
    }

    @Override
    public void changePassword(String memberName, ChangePasswordRequestDto changePasswordRequestDto) {
        Member member = memberRepository.getMemberByName(memberName)
                .orElseThrow(() -> new ApiException(999, "회원정보가 존재하지 않습니다."));

        if(!passwordEncoder.matches(changePasswordRequestDto.getPrevPassword(), member.getMemberPassword())) {
            throw new ApiException(999, "회원정보가 일치하지 않습니다.");
        }

        member.setMemberPassword(passwordEncoder.encode(changePasswordRequestDto.getNewPassword()));

        memberRepository.updateMember(member);
    }
}
