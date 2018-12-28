package com.hecheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableHystrix
@SpringBootApplication
@EnableFeignClients
public class SpringCloudDemoEurekaFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudDemoEurekaFeignApplication.class, args);
	}

}

