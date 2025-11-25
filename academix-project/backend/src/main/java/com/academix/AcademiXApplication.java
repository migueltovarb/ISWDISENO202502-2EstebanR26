package com.academix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class AcademiXApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(AcademiXApplication.class, args);
        System.out.println("===========================================");
        System.out.println("AcademiX Backend - Sistema de Inscripciones");
        System.out.println("Puerto: 8080");
        System.out.println("API: http://localhost:8080/api");
        System.out.println("===========================================");
    }
}
