package com.cibertec.event_service.cloudinary;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "nombre");
        config.put("api_key", "clave");
        config.put("api_secret", "clave");
        return new Cloudinary(config);
    }
}