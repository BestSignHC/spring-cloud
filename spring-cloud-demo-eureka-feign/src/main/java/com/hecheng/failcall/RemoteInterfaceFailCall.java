package com.hecheng.failcall;

import com.hecheng.RemoteInterface;
import org.springframework.stereotype.Component;

@Component
public class RemoteInterfaceFailCall implements RemoteInterface{

    /**
     * 服务调用失败时的方法
     * @return
     */
    @Override
    public String pingAction() {
        return "fail call.";
    }
}
