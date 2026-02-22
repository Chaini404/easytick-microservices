package com.cibertec.payment_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import io.github.cdimascio.dotenv.Dotenv;


@SpringBootApplication
@EnableDiscoveryClient
public class PaymentServiceApplication {

	public static void main(String[] args) {

			// Carga las variables del .env
        Dotenv dotenv = Dotenv.configure()
                .directory("payment-service/payment-service") // ruta donde está TU .env
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

		//variable de paypal
		System.setProperty("PAYPAL_CLIENT_ID", dotenv.get("PAYPAL_CLIENT_ID"));
		System.setProperty("PAYPAL_CLIENT_SECRET", dotenv.get("PAYPAL_CLIENT_SECRET"));
		System.setProperty("PAYPAL_API_URL", dotenv.get("PAYPAL_API_URL"));
		System.setProperty("PAYPAL_RETURN_URL", dotenv.get("PAYPAL_RETURN_URL"));
		System.setProperty("PAYPAL_CANCEL_URL", dotenv.get("PAYPAL_CANCEL_URL"));
		


		SpringApplication.run(PaymentServiceApplication.class, args);
	}

}
