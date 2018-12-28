package com.hecheng;

import com.hecheng.failcall.RemoteInterfaceFailCall;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

// 通过fallback指定失败时候调用的类
@FeignClient(name = "hc-eureka-producer", fallback = RemoteInterfaceFailCall.class)
public interface RemoteInterface {
    @GetMapping("/producer")
    String pingAction();

}
