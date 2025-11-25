package com.academix.controllers;

import com.academix.models.Inscripcion;
import com.academix.services.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/inscripciones")
@CrossOrigin(origins = "*")
public class InscripcionController {
    
    @Autowired
    private InscripcionService inscripcionService;
    
    @GetMapping
    public ResponseEntity<List<Inscripcion>> listar() {
        return ResponseEntity.ok(inscripcionService.listarTodas());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> obtenerPorId(@PathVariable String id) {
        return ResponseEntity.ok(inscripcionService.obtenerPorId(id));
    }
    
    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<Inscripcion>> obtenerPorEstudiante(@PathVariable String estudianteId) {
        return ResponseEntity.ok(inscripcionService.obtenerPorEstudiante(estudianteId));
    }
    
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Inscripcion>> obtenerPorCurso(@PathVariable String cursoId) {
        return ResponseEntity.ok(inscripcionService.obtenerPorCurso(cursoId));
    }
    
    @PostMapping
    public ResponseEntity<Inscripcion> inscribirse(@RequestBody Inscripcion inscripcion) {
        return ResponseEntity.ok(inscripcionService.inscribirse(inscripcion));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Inscripcion> actualizar(@PathVariable String id, @RequestBody Inscripcion inscripcion) {
        return ResponseEntity.ok(inscripcionService.actualizar(id, inscripcion));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        inscripcionService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}