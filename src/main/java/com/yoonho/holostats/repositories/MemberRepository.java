package com.yoonho.holostats.repositories;

import com.yoonho.holostats.models.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface MemberRepository {
    Optional<Member> getMemberById(@Param("memberId") Integer memberId);
    Optional<Member> getMemberByName(@Param("memberName") String memberName);
    void insertMember(@Param("member") Member member);
}
