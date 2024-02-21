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
import java.util.Optional;

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
    List<Bbs> getBbsByLsId(@Param("lsId") Integer lsId, @Param("pageInfo") PageInfo pageInfo, @Param("memberId") Integer memberId);
    void insertBbs(@Param("bbs") Bbs bbs);
    void recommend(@Param("bbsId") Integer bbsId, @Param("memberId") Integer memberId);
    void cancelRecommend(@Param("bbsId") Integer bbsId, @Param("memberId") Integer memberId);
    Optional<Bbs> getBbsById(@Param("bbsId") Integer bbsId);
    void updateBbs(@Param("bbsContent") String content, @Param("bbsId") Integer bbsId);
    void deleteBbs(@Param("bbsId") Integer bbsId);
    void deleteRecommend(@Param("bbsId") Integer bbsId);
}
