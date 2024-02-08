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

package com.yoonho.holoboard.models;

import lombok.Data;

import java.sql.Timestamp;

/**
 * packageName    : com.yoonho.holostats.models
 * fileName       : DbFile
 * author         : kim-yoonho
 * date           : 1/4/24
 * description    : 파일 객체
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/4/24        kim-yoonho       최초 생성
 */

@Data
public class DbFile {

    private Integer fileId;         //파일아이디
    private String fileType;        //파일 유형
    private String filePath;        //파일 경로
    private String fileUrl;         //파일 url
    private String fileName;        //파일명
    private String fileExt;         //파일확장자
    private Long fileSize;        //파일크기
    private Timestamp createTime;   //생성일시
    private Timestamp updateTime;   //수정일시

}
