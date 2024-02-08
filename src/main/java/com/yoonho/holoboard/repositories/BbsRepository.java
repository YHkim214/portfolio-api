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

package com.yoonho.holoboard.repositories;

import com.yoonho.holoboard.common.PageInfo;
import com.yoonho.holoboard.models.Bbs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * packageName    : com.yoonho.holostats.repositories
 * fileName       : BBSRepository
 * author         : kim-yoonho
 * date           : 1/31/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/31/24        kim-yoonho       최초 생성
 */
@Mapper
public interface BbsRepository {
    List<Bbs> getBbsByLsId(@Param("lsId") Integer lsId, @Param("pageInfo") PageInfo pageInfo);
    void insertBbs(@Param("bbs") Bbs bbs);
}
