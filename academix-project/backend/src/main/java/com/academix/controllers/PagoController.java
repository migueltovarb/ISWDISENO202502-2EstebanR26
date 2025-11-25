package com.academix.controllers;

import com.academix.models.Pago;
import com.academix.services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pagos")
@CrossOrigin(origins = "*")
public class PagoController {
    
    @Autowired
    private PagoService pagoService;
    
    @GetMapping
    public ResponseEntity<List<Pago>> listar() {
        return ResponseEntity.ok(pagoService.listarTodos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtenerPorId(@PathVariable String id) {
        return ResponseEntity.ok(pagoService.obtenerPorId(id));
    }
    
    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<Pago>> obtenerPorEstudiante(@PathVariable String estudianteId) {
        return ResponseEntity.ok(pagoService.obtenerPorEstudiante(estudianteId));
    }
    
    @PostMapping
    public ResponseEntity<Pago> crear(@RequestBody Pago pago) {
        return ResponseEntity.ok(pagoService.crear(pago));
    }
    
    @PutMapping("/{id}/confirmar")
    public ResponseEntity<Pago> confirmarPago(@PathVariable String id, @RequestBody Map<String, String> datos) {
        try {
            String transaccionId = datos.get("transaccionId");
            String metodoPago = datos.get("metodoPago");
            
            // Usar el método del servicio que ya maneja todo
            Pago pagoConfirmado = pagoService.confirmarPago(id, transaccionId);
            
            // Actualizar el método de pago si se proporcionó
            if (metodoPago != null && !metodoPago.isEmpty()) {
                pagoConfirmado.setMetodoPago(metodoPago);
                pagoConfirmado = pagoService.actualizar(id, pagoConfirmado);
            }
            
            return ResponseEntity.ok(pagoConfirmado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Pago> actualizar(@PathVariable String id, @RequestBody Pago pago) {
        return ResponseEntity.ok(pagoService.actualizar(id, pago));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        pagoService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}