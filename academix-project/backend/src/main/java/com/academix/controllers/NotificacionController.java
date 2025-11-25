package com.academix.controllers;

import com.academix.models.Notificacion;
import com.academix.services.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notificaciones")
@CrossOrigin(origins = "*")
public class NotificacionController {
    
    @Autowired
    private NotificacionService notificacionService;
    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Notificacion>> obtenerPorUsuario(@PathVariable String usuarioId) {
        return ResponseEntity.ok(notificacionService.obtenerPorUsuario(usuarioId));
    }
    
    @GetMapping("/usuario/{usuarioId}/no-leidas")
    public ResponseEntity<List<Notificacion>> obtenerNoLeidas(@PathVariable String usuarioId) {
        return ResponseEntity.ok(notificacionService.obtenerNoLeidas(usuarioId));
    }
    
    @GetMapping("/usuario/{usuarioId}/contar-no-leidas")
    public ResponseEntity<Map<String, Long>> contarNoLeidas(@PathVariable String usuarioId) {
        long count = notificacionService.contarNoLeidas(usuarioId);
        Map<String, Long> response = new HashMap<>();
        response.put("count", count);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping
    public ResponseEntity<Notificacion> crear(@RequestBody Map<String, String> datos) {
        Notificacion notificacion = notificacionService.crearNotificacion(
            datos.get("usuarioId"),
            datos.get("titulo"),
            datos.get("mensaje"),
            datos.get("categoria"),
            datos.get("referenciaTipo"),
            datos.get("referenciaId")
        );
        return ResponseEntity.ok(notificacion);
    }
    
    @PutMapping("/{id}/leer")
    public ResponseEntity<Notificacion> marcarComoLeida(@PathVariable String id) {
        return ResponseEntity.ok(notificacionService.marcarComoLeida(id));
    }
    
    @PutMapping("/usuario/{usuarioId}/leer-todas")
    public ResponseEntity<Void> marcarTodasComoLeidas(@PathVariable String usuarioId) {
        notificacionService.marcarTodasComoLeidas(usuarioId);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        notificacionService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}