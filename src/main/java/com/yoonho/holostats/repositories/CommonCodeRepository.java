package com.yoonho.holostats.repositories;

import com.yoonho.holostats.models.CommonCode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * packageName    : com.yoonho.holostats.repository
 * fileName       : CommonCodeRepository
 * author         : kim-yoonho
 * date           : 12/21/23
 * description    : 공통코드 db 통신 객체
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/21/23        kim-yoonho       최초 생성
 */

@Mapper
public interface CommonCodeRepository {
    List<CommonCode> getAllCommonCodes();
}
