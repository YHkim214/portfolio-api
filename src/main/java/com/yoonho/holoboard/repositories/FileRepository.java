package com.yoonho.holoboard.repositories;

import com.yoonho.holoboard.models.DbFile;
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
