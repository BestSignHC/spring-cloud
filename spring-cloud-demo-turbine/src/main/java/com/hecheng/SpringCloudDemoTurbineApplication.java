package com.hecheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableTurbine
@SpringBootApplication
public class SpringCloudDemoTurbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudDemoTurbineApplication.class, args);
	}

}

