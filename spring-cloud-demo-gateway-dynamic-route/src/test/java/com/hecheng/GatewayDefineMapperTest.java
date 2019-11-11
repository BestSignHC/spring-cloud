package com.hecheng;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hecheng.entity.GatewayDefine;
import com.hecheng.mapper.GatewayDefineMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GatewayDefineMapperTest {
    @Autowired
    private GatewayDefineMapper mapper;

    @Test
    public void testFindAll() {
        String s = JSON.toJSONString(mapper.findAll());
        System.out.println("*******");
        System.out.println(s);
        System.out.println("*******");
    }

    public void testInsert() {
        System.out.println("start test jdbc.........");
        GatewayDefine gatewayDefine = new GatewayDefine();
        gatewayDefine.setId(UUID.randomUUID().toString());
        gatewayDefine.setName("test_gateway");
        gatewayDefine.setUri("test_uri");
        gatewayDefine.setFilters("test_filters");
        gatewayDefine.setPredicates("test_predicates");

        mapper.add(gatewayDefine);
    }
}
