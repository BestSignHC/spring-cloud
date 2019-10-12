package com.hecheng;

import com.hecheng.filter.LimitFilter;
import com.hecheng.filter.RequestLogFilter;
import com.hecheng.filter.ResponssePostFilter;
import com.hecheng.filter.SignFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public SignFilter signFilter() {
        return new SignFilter();
    }

    @Bean
    public RequestLogFilter requestLogFilter() {
        return new RequestLogFilter();
    }

    @Bean
    public LimitFilter limitFilter() {
        return new LimitFilter();
    }

    @Bean
    public ResponssePostFilter responssePostFilter() {
        return new ResponssePostFilter();
    }

}
