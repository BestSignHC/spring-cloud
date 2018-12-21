package com.hecheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringCloudDemoEurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudDemoEurekaClientApplication.class, args);
	}

}

