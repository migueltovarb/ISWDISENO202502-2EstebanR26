package com.academix.services;

import com.academix.models.RecuperacionPassword;
import com.academix.models.Usuario;
import com.academix.repositories.RecuperacionPasswordRepository;
import com.academix.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class RecuperacionPasswordService {
    
    @Autowired
    private RecuperacionPasswordRepository recuperacionRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public RecuperacionPassword solicitarRecuperacion(String email) {
        // Verificar que el usuario existe
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("No existe un usuario con ese email");
        }
        
        Usuario usuario = usuarioOpt.get();
        
        // Generar token de 6 dígitos
        String token = generarToken();
        
        // Crear solicitud de recuperación
        RecuperacionPassword recuperacion = new RecuperacionPassword();
        recuperacion.setUsuarioId(usuario.getId());
        recuperacion.setEmail(email);
        recuperacion.setToken(token);
        recuperacion.setFechaCreacion(LocalDateTime.now());
        recuperacion.setFechaExpiracion(LocalDateTime.now().plusHours(1)); // Expira en 1 hora
        recuperacion.setUsado(false);
        
        RecuperacionPassword guardada = recuperacionRepository.save(recuperacion);
        
        // TODO: Aquí enviarías el email con el token
        // Por ahora, solo imprimimos en consola
        System.out.println("=================================");
        System.out.println("CÓDIGO DE RECUPERACIÓN");
        System.out.println("Email: " + email);
        System.out.println("Token: " + token);
        System.out.println("Expira en 1 hora");
        System.out.println("=================================");
        
        return guardada;
    }
    
    public void restablecerPassword(String token, String nuevaPassword) {
        // Buscar token válido
        Optional<RecuperacionPassword> recuperacionOpt = 
            recuperacionRepository.findByTokenAndUsadoFalse(token);
        
        if (recuperacionOpt.isEmpty()) {
            throw new RuntimeException("Token inválido o ya usado");
        }
        
        RecuperacionPassword recuperacion = recuperacionOpt.get();
        
        // Verificar que no haya expirado
        if (LocalDateTime.now().isAfter(recuperacion.getFechaExpiracion())) {
            throw new RuntimeException("El token ha expirado");
        }
        
        // Actualizar contraseña del usuario
        Usuario usuario = usuarioRepository.findById(recuperacion.getUsuarioId())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        usuario.setPassword(nuevaPassword);
        usuarioRepository.save(usuario);
        
        // Marcar token como usado
        recuperacion.setUsado(true);
        recuperacionRepository.save(recuperacion);
    }
    
    public RecuperacionPassword verificarToken(String token) {
        Optional<RecuperacionPassword> recuperacionOpt = 
            recuperacionRepository.findByTokenAndUsadoFalse(token);
        
        if (recuperacionOpt.isEmpty()) {
            throw new RuntimeException("Token inválido o ya usado");
        }
        
        RecuperacionPassword recuperacion = recuperacionOpt.get();
        
        if (LocalDateTime.now().isAfter(recuperacion.getFechaExpiracion())) {
            throw new RuntimeException("El token ha expirado");
        }
        
        return recuperacion;
    }
    
    private String generarToken() {
        Random random = new Random();
        int token = 100000 + random.nextInt(900000); // Genera número de 6 dígitos
        return String.valueOf(token);
    }
}