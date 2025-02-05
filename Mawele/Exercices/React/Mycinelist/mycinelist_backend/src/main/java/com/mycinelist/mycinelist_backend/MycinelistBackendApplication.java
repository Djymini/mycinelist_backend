package com.mycinelist.mycinelist_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MycinelistBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MycinelistBackendApplication.class, args);
	}

}
