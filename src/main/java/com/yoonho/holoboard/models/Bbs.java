package com.yoonho.holoboard.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

/**
 * packageName    : com.yoonho.holostats.models
 * fileName       : BBS
 * author         : kim-yoonho
 * date           : 1/31/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/31/24        kim-yoonho       최초 생성
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bbs {
    private Integer bbsId;          /** 게시글 아이디 **/
    private Integer memberId;       /** 작성자 아이디 **/
    private Integer lsId;           /** 라이브 스트림 아이디 **/
    private String bbsContent;      /** 게시글 내용 **/
    private Integer bbsGoodCnt;     /** 게시글 좋아요 수 **/
    private String bbsType;         /** 게시글 종류 **/
    private Integer bbsParentId;    /** 댓글 부모 아이디 **/
    private String bbsStatus;       /** 게시글 상태 **/
    private Timestamp createTime;   /** 게시글 작성시각 **/
    private Timestamp updateTime;   /** 게시글 수정시각 **/

    private Member member;
    private List<Bbs> children;

    private Boolean isRecommended;
}
