package com.hecheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCloudDemoGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudDemoGatewayApplication.class, args);
	}

//	@Bean(name="hostAddrKeyResolver")
//	public HostAddrKeyResolver hostAddrKeyResolver() {
//		return new HostAddrKeyResolver();
//	}

}
