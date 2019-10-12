package com.hecheng.filter;

import com.hecheng.ThreadLocalHolder;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class RequestLogFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
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
        HttpServletRequest request = requestContext.getRequest();

        long time = System.currentTimeMillis();
        System.out.printf("[%d]: request uri: %s%n", time, request.getRequestURI());
        System.out.printf("[%d]: request method: %s%n", time, request.getMethod());

        // 记录请求到达时间
        ThreadLocalHolder.requestTimeHolder().set(time);

        String requestQueryParams = request.getQueryString();
        System.out.printf("[%d]: request query params: %s%n", time, requestQueryParams);

        try {
            InputStream inputStream = request.getInputStream();
            String requestBody = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
            System.out.printf("[%d]: request requestBody: %s%n", time, requestBody);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
