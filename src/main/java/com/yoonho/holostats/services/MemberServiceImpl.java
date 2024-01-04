package com.yoonho.holostats.services;

import com.yoonho.holostats.exceptions.ApiException;
import com.yoonho.holostats.common.CommonCodes;
import com.yoonho.holostats.dtos.request.RegisterMemberRequestDto;
import com.yoonho.holostats.models.Member;
import com.yoonho.holostats.repositories.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final FileService fileService;

    private final PasswordEncoder passwordEncoder;

    private final Logger log =  LoggerFactory.getLogger(this.getClass().getSimpleName());

    public MemberServiceImpl(MemberRepository memberRepository,
                             FileService fileService,
                             PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.fileService = fileService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerMember(RegisterMemberRequestDto registerMemberRequestDto) throws IOException {
        //중복체크
        Optional<Member> memberDb = memberRepository.getMemberByName(registerMemberRequestDto.getMemberName());

        if(memberDb.isPresent()) {
            throw new ApiException(
                    Integer.parseInt(CommonCodes.ERROR_CODE.ERROR_CODE_DUP_MEMBER.VAL)
                    , CommonCodes.ERROR_CODE.ERROR_CODE_DUP_MEMBER.DESC);
        }

        Member member = new Member();

        member.setMemberName(registerMemberRequestDto.getMemberName());
        member.setMemberPassword(passwordEncoder.encode(registerMemberRequestDto.getMemberPassword()));
        member.setMemberNickName(registerMemberRequestDto.getMemberNickName());

        member.setMemberThumbnailId(0);

        member.setMemberRole(CommonCodes.MEMBER_ROLE.ROLE_USER.CODE);
        member.setMemberStatus(CommonCodes.MEMBER_STATUS.MEMBER_STATUS_REGISTERED.CODE);

        memberRepository.insertMember(member);

        //섬네일 파일 등록
        fileService.registerThumbnailFile(registerMemberRequestDto.getMemberThumbnailFile(), member.getMemberId());
    }
}
