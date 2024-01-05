package com.yoonho.holostats.repositories;

import com.yoonho.holostats.models.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * packageName    : com.yoonho.holostats.repository
 * fileName       : CommonCodeRepository
 * author         : kim-yoonho
 * date           : 12/21/23
 * description    : 회원 정보 통신 객체
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/21/23        kim-yoonho       최초 생성
 */
@Mapper
public interface MemberRepository {
    Optional<Member> getMemberById(@Param("memberId") Integer memberId);
    Optional<Member> getMemberByName(@Param("memberName") String memberName);
    void insertMember(@Param("member") Member member);
    void updateMember(@Param("member") Member member);
}
