package com.hecheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SpringCloudDemoConsumerFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudDemoConsumerFeignApplication.class, args);
	}

}
