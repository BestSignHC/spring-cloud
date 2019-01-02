package com.hecheng;

import com.hecheng.filter.CustomFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class SpringCloudDemoApiGatewayApplication {

	//申明filter
	@Bean
	public CustomFilter customFilter() {
		return new CustomFilter();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudDemoApiGatewayApplication.class, args);
	}

}

