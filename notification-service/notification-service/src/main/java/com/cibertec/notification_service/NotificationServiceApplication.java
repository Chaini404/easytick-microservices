package com.cibertec.notification_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.github.cdimascio.dotenv.Dotenv;


@EnableFeignClients
@SpringBootApplication
public class NotificationServiceApplication {

    public static void main(String[] args) {
    	   Dotenv dotenv = Dotenv.load();

           // --- Database ---
           System.setProperty("DB_URL", dotenv.get("DB_URL"));
           System.setProperty("DB_USER", dotenv.get("DB_USER"));
           System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

           // --- Eureka ---
           System.setProperty("EUREKA_URL", dotenv.get("EUREKA_URL"));

           // --- RabbitMQ ---
           System.setProperty("RABBIT_HOST", dotenv.get("RABBIT_HOST"));
           System.setProperty("RABBIT_PORT", dotenv.get("RABBIT_PORT"));
           System.setProperty("RABBIT_USER", dotenv.get("RABBIT_USER"));
           System.setProperty("RABBIT_PASSWORD", dotenv.get("RABBIT_PASSWORD"));

           // --- Mail ---
           System.setProperty("MAIL_HOST", dotenv.get("MAIL_HOST"));
           System.setProperty("MAIL_PORT", dotenv.get("MAIL_PORT"));
           System.setProperty("MAIL_USERNAME", dotenv.get("MAIL_USERNAME"));
           System.setProperty("MAIL_PASSWORD", dotenv.get("MAIL_PASSWORD"));
           System.setProperty("MAIL_PROPERTIES_MAIL_SMTP_AUTH",
                   dotenv.get("MAIL_PROPERTIES_MAIL_SMTP_AUTH"));
           System.setProperty("MAIL_PROPERTIES_MAIL_SMTP_STARTTLS_ENABLE",
                   dotenv.get("MAIL_PROPERTIES_MAIL_SMTP_STARTTLS_ENABLE"));
        // Inicia Spring Boot
        SpringApplication.run(NotificationServiceApplication.class, args);
    }
}