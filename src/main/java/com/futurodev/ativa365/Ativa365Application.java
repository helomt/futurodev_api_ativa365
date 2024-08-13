package com.futurodev.ativa365;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Ativa365Application {

	public static void main(String[] args) {
		SpringApplication.run(Ativa365Application.class, args);
	}

}
