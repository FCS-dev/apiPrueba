package com.fcs.apiPrueba.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class CorsGlobalConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Aplica a todas las rutas
                .allowedOrigins("*")  // Permitir solo localhost:5500
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");  // Métodos permitidos
    }
}
