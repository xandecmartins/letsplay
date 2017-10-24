package com.letsplay.server;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LetsPlayerServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LetsPlayerServerApplication.class, args);
	}
}
