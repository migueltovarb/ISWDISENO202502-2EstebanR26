package com.example.vehicleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VehicleApiApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(VehicleApiApplication.class, args);
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║     🚗 Vehicle API está ejecutándose en:             ║");
        System.out.println("║     http://localhost:8080                             ║");
        System.out.println("║                                                        ║");
        System.out.println("║     Endpoints disponibles:                             ║");
        System.out.println("║     • GET    /api/vehicles                            ║");
        System.out.println("║     • GET    /api/vehicles/{id}                       ║");
        System.out.println("║     • POST   /api/vehicles                            ║");
        System.out.println("║     • PUT    /api/vehicles/{id}                       ║");
        System.out.println("║     • DELETE /api/vehicles/{id}                       ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");
    }
}
