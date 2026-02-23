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

	  /*  // Carga las variables del .env
        Dotenv dotenv = Dotenv.configure()
                .directory("booking-service/booking-service") // ruta donde está TU .env
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
*/
	    SpringApplication.run(BookingServiceApplication.class, args);
	}

	private static void setIfNotNull(String key, String value) {
	    if (value != null) {
	        System.setProperty(key, value);
	    }
	}


}
