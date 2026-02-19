package com.cibertec.event_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableDiscoveryClient
public class EventServiceApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.configure()
				// .directory("event-service/event-service") // Antes
				.ignoreIfMissing().load();

		setIfNotNull("DB_URL", dotenv.get("DB_URL"));
		setIfNotNull("DB_USER", dotenv.get("DB_USER"));
		setIfNotNull("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

		setIfNotNull("RABBIT_HOST", dotenv.get("RABBIT_HOST"));
		setIfNotNull("RABBIT_PORT", dotenv.get("RABBIT_PORT"));
		setIfNotNull("RABBIT_USER", dotenv.get("RABBIT_USER"));
		setIfNotNull("RABBIT_PASSWORD", dotenv.get("RABBIT_PASSWORD"));

		setIfNotNull("EUREKA_URL", dotenv.get("EUREKA_URL"));

		SpringApplication.run(EventServiceApplication.class, args);
	}

	private static void setIfNotNull(String key, String value) {
		if (value != null) {
			System.setProperty(key, value);
		}

	}
}
