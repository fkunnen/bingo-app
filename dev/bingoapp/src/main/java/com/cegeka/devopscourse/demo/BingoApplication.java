package com.cegeka.devopscourse.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class BingoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BingoApplication.class, args);
	}
}
