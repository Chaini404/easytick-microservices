package com.cibertec.booking_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BookingServiceApplication {

	public static void main(String[] args) {
	    SpringApplication.run(BookingServiceApplication.class, args);
	}

	private static void setIfNotNull(String key, String value) {
	    if (value != null) {
	        System.setProperty(key, value);
	    }
	}


}
