package com.cibertec.notification_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class NotificationServiceApplication {

    public static void main(String[] args) {

        // Inicia Spring Boot
        SpringApplication.run(NotificationServiceApplication.class, args);
    }
}