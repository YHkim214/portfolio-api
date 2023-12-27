package com.yoonho.holostats.services;

import com.yoonho.holostats.exceptions.ApiException;
import com.yoonho.holostats.common.CommonCodes;
import com.yoonho.holostats.dtos.RegisterMemberDto;
import com.yoonho.holostats.models.Member;
import com.yoonho.holostats.repositories.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public MemberServiceImpl(MemberRepository memberRepository,
                             FileService fileService,
                             PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.fileService = fileService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerMember(RegisterMemberDto registerMemberDto) {
        //중복체크
        Optional<Member> memberDb = memberRepository.getMemberByName(registerMemberDto.getMemberName());

        if(memberDb.isPresent()) {
            throw new ApiException(
                    Integer.parseInt(CommonCodes.ERROR_CODE.ERROR_CODE_API.VAL)
                    , CommonCodes.ERROR_CODE.ERROR_CODE_API.DESC);
        }

        Member member = new Member();

        member.setMemberName(registerMemberDto.getMemberName());
        member.setMemberPassword(passwordEncoder.encode(registerMemberDto.getMemberPassword()));
        member.setMemberNickName(registerMemberDto.getMemberNickName());

        //TODO 파일 등록 비즈니스 로직 개발
        member.setMemberThumbnailId(0);

        member.setMemberRole(CommonCodes.MEMBER_ROLE.ROLE_USER.CODE);
        member.setMemberStatus(CommonCodes.MEMBER_STATUS.MEMBER_STATUS_REGISTERED.CODE);

        memberRepository.insertMember(member);
    }
}
