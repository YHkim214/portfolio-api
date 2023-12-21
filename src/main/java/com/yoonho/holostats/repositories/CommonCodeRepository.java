package com.yoonho.holostats.repositories;

import com.yoonho.holostats.models.CommonCode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommonCodeRepository {
    List<CommonCode> getAllCommonCodes();
}
