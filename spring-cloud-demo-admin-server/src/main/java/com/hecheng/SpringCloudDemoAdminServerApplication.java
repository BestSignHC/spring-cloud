package com.hecheng;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableDiscoveryClient
@EnableAdminServer
@SpringBootApplication
public class SpringCloudDemoAdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudDemoAdminServerApplication.class, args);
	}

}
