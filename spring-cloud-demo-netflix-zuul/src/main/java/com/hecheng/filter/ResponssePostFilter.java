package com.hecheng.filter;

import com.alibaba.fastjson.JSONObject;
import com.hecheng.ThreadLocalHolder;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 后置过滤器
 */
public class ResponssePostFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();

        // 去除请求到达时间
        Long requestTime = ThreadLocalHolder.requestTimeHolder().get();
        long cost = System.currentTimeMillis() - requestTime;

        try {
            InputStream inputStream = requestContext.getResponseDataStream();
            String responseBody = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));

            // 包装返回
            JSONObject wrapperResponse = new JSONObject();
            wrapperResponse.put("cost", cost);
            wrapperResponse.put("content", responseBody);

            System.out.printf("[%d] response: %s\n", System.currentTimeMillis(), wrapperResponse.toJSONString());

            requestContext.setResponseBody(wrapperResponse.toJSONString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
