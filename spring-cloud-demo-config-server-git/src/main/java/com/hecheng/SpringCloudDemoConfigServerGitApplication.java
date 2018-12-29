package com.hecheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SpringCloudDemoConfigServerGitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudDemoConfigServerGitApplication.class, args);
	}

}

