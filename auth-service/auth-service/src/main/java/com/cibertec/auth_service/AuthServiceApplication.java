package com.cibertec.auth_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthServiceApplication {

    public static void main(String[] args) {

        // Cargar variables de entorno desde .env
        Dotenv dotenv = Dotenv.configure()
            .directory("./") // Busca en la raíz del proyecto auth-service
            .ignoreIfMissing() // No falla si no encuentra el .env
            .load();

        System.setProperty("DB_URL", dotenv.get("DB_URL", "jdbc:mysql://localhost:3306/auth_db?createDatabaseIfNotExist=true"));
        System.setProperty("DB_USER", dotenv.get("DB_USER", "root"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD", "mysql"));

        System.setProperty("RABBIT_HOST", dotenv.get("RABBIT_HOST", "localhost"));
        System.setProperty("RABBIT_PORT", dotenv.get("RABBIT_PORT", "5672"));
        System.setProperty("RABBIT_USER", dotenv.get("RABBIT_USER", "guest"));
        System.setProperty("RABBIT_PASSWORD", dotenv.get("RABBIT_PASSWORD", "guest"));

        System.setProperty("EUREKA_URL", dotenv.get("EUREKA_URL", "http://localhost:8761/eureka/"));

        SpringApplication.run(AuthServiceApplication.class, args);
    }
}
