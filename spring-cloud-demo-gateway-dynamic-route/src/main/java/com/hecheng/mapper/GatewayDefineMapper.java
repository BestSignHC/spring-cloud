package com.hecheng.mapper;

import com.hecheng.entity.GatewayDefine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GatewayDefineMapper {

    void add(GatewayDefine gatewayDefine);

    List<GatewayDefine> findAll();

    void delete(@Param("id") String id);

    GatewayDefine findOne(@Param("id") String id);
}
