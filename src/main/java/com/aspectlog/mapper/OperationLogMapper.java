package com.aspectlog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface OperationLogMapper {
    void insertUserOptionLog(Map map);

    void createTable(@Param("table") String table);
}
