package com.cibertec.event_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import io.github.cdimascio.dotenv.Dotenv;


@SpringBootApplication
@EnableDiscoveryClient
public class EventServiceApplication {

	public static void main(String[] args) {
/*
		// Carga las variables del .env
        Dotenv dotenv = Dotenv.configure()
                .directory("event-service/event-service") // ruta donde está TU .env
                .ignoreIfMissing()
                .load();

        // Variables de base de datos
        System.setProperty("DB_URL", dotenv.get("DB_URL"));
        System.setProperty("DB_USER", dotenv.get("DB_USER"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

        // Variables de RabbitMQ
        System.setProperty("RABBIT_HOST", dotenv.get("RABBIT_HOST"));
        System.setProperty("RABBIT_PORT", dotenv.get("RABBIT_PORT"));
        System.setProperty("RABBIT_USER", dotenv.get("RABBIT_USER"));
        System.setProperty("RABBIT_PASSWORD", dotenv.get("RABBIT_PASSWORD"));

        // Variable de Eureka
        System.setProperty("EUREKA_URL", dotenv.get("EUREKA_URL"));


		System.setProperty("CLOUDINARY_CLOUD_NAME", dotenv.get("CLOUDINARY_CLOUD_NAME"));
		System.setProperty("CLOUDINARY_API_KEY", dotenv.get("CLOUDINARY_API_KEY"));
		System.setProperty("CLOUDINARY_API_SECRET", dotenv.get("CLOUDINARY_API_SECRET"));
*/
		SpringApplication.run(EventServiceApplication.class, args);
	}

}
