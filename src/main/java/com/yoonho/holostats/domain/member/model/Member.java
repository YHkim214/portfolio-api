package com.yoonho.holostats.domain.member.model;

import lombok.Data;

import java.sql.Timestamp;

/*회원 정보 객체*/
@Data
public class Member {

    private Integer memberId;           /*회원 아이디*/
    private String memberEmail;         /*회원 이메일*/
    private String memberNickName;      /*회원 닉네임*/
    private String memberPassword;      /*회원 비밀번호*/
    private Integer memberThumbnailId;  /*회원 섬네일 아이디*/
    private String memberStatus;        /*회원 상태*/
    private Timestamp createTime;       /*가입일*/
    private Timestamp updateTime;       /*마지막 수정일*/

}
