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

	    Dotenv dotenv = Dotenv.configure()
	            .ignoreIfMissing()
	            .load();

	    setIfNotNull("DB_URL", dotenv.get("DB_URL"));
	    setIfNotNull("DB_USER", dotenv.get("DB_USER"));
	    setIfNotNull("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

	    setIfNotNull("RABBIT_HOST", dotenv.get("RABBIT_HOST"));
	    setIfNotNull("RABBIT_PORT", dotenv.get("RABBIT_PORT"));
	    setIfNotNull("RABBIT_USER", dotenv.get("RABBIT_USER"));
	    setIfNotNull("RABBIT_PASSWORD", dotenv.get("RABBIT_PASSWORD"));

	    setIfNotNull("EUREKA_URL", dotenv.get("EUREKA_URL"));

	    SpringApplication.run(BookingServiceApplication.class, args);
	}

	private static void setIfNotNull(String key, String value) {
	    if (value != null) {
	        System.setProperty(key, value);
	    }
	}


}
