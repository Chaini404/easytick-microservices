package com.cibertec.auth_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthServiceApplication {

	public static void main(String[] args) {
        
    Dotenv dotenv = Dotenv.configure()
        .directory("auth-service/auth-service") // ruta donde está TU .env
        .ignoreIfMissing()
        .load();


    System.setProperty("DB_URL", dotenv.get("DB_URL"));
    System.setProperty("DB_USER", dotenv.get("DB_USER"));
    System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

    System.setProperty("RABBIT_HOST", dotenv.get("RABBIT_HOST"));
    System.setProperty("RABBIT_PORT", dotenv.get("RABBIT_PORT"));
    System.setProperty("RABBIT_USER", dotenv.get("RABBIT_USER"));
    System.setProperty("RABBIT_PASSWORD", dotenv.get("RABBIT_PASSWORD"));

    System.setProperty("EUREKA_URL", dotenv.get("EUREKA_URL"));

    SpringApplication.run(AuthServiceApplication.class, args);
}
}
