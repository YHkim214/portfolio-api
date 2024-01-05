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

package com.yoonho.holostats.repositories;

import com.yoonho.holostats.models.DbFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * packageName    : com.yoonho.holostats.repositories
 * fileName       : FileRepository
 * author         : kim-yoonho
 * date           : 1/4/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/4/24        kim-yoonho       최초 생성
 */

@Mapper
public interface FileRepository {

    void insertFile(@Param("file") DbFile dbFile);

}
