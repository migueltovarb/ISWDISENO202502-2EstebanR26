package com.academix.controllers;

import com.academix.models.RecuperacionPassword;
import com.academix.services.RecuperacionPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/recuperacion-password")
@CrossOrigin(origins = "*")
public class RecuperacionPasswordController {
    
    @Autowired
    private RecuperacionPasswordService recuperacionService;
    
    @PostMapping("/solicitar")
    public ResponseEntity<?> solicitarRecuperacion(@RequestBody Map<String, String> datos) {
        try {
            String email = datos.get("email");
            RecuperacionPassword recuperacion = recuperacionService.solicitarRecuperacion(email);
            
            return ResponseEntity.ok(Map.of(
                "mensaje", "Se ha enviado un código de recuperación a tu email",
                "token", recuperacion.getToken() // En desarrollo, para facilitar pruebas
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @PostMapping("/verificar-token")
    public ResponseEntity<?> verificarToken(@RequestBody Map<String, String> datos) {
        try {
            String token = datos.get("token");
            RecuperacionPassword recuperacion = recuperacionService.verificarToken(token);
            
            return ResponseEntity.ok(Map.of(
                "mensaje", "Token válido",
                "email", recuperacion.getEmail()
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @PostMapping("/restablecer")
    public ResponseEntity<?> restablecerPassword(@RequestBody Map<String, String> datos) {
        try {
            String token = datos.get("token");
            String nuevaPassword = datos.get("nuevaPassword");
            
            recuperacionService.restablecerPassword(token, nuevaPassword);
            
            return ResponseEntity.ok(Map.of(
                "mensaje", "Contraseña restablecida exitosamente"
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}