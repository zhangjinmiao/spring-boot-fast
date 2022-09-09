package com.zjm.springbootfast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringBootFastApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFastApplication.class, args);
	}

}
