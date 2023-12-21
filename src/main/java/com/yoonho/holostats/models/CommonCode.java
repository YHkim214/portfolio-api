package com.yoonho.holostats.models;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;

/**
 * packageName    : com.yoonho.holostats.models
 * fileName       : CommonCode
 * author         : kim-yoonho
 * date           : 12/21/23
 * description    : 공통코드 객체
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/21/23        kim-yoonho       최초 생성
 */
@Data
public class CommonCode {

   private int CommonCodeId;            /*공통코드 id*/
   private String CommonCodeGroup;      /*공통코드 그룹*/
   private String CommonCodeGroupDesc;  /*공통코드 그룹 설명*/
   private String CommonCode;           /*공통코드*/
   private String CommonCodeDesc;       /*공통코드 설명*/
   private Timestamp createTime;        /*생성시각*/
   private Timestamp updateTime;        /*수정시각*/

}
