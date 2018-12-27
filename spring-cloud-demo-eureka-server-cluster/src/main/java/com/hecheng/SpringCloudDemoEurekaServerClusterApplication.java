package com.hecheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringCloudDemoEurekaServerClusterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudDemoEurekaServerClusterApplication.class, args);
	}

}

