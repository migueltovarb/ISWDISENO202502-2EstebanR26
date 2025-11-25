package com.academix.controllers;

import com.academix.models.Calificacion;
import com.academix.services.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/calificaciones")
@CrossOrigin(origins = "*")
public class CalificacionController {
    
    @Autowired
    private CalificacionService calificacionService;
    
    @GetMapping
    public ResponseEntity<List<Calificacion>> listar() {
        return ResponseEntity.ok(calificacionService.listarTodas());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Calificacion> obtenerPorId(@PathVariable String id) {
        return ResponseEntity.ok(calificacionService.obtenerPorId(id));
    }
    
    @GetMapping("/inscripcion/{inscripcionId}")
    public ResponseEntity<Calificacion> obtenerPorInscripcion(@PathVariable String inscripcionId) {
        Calificacion calificacion = calificacionService.obtenerPorInscripcion(inscripcionId);
        if (calificacion != null) {
            return ResponseEntity.ok(calificacion);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<Calificacion>> obtenerPorEstudiante(@PathVariable String estudianteId) {
        return ResponseEntity.ok(calificacionService.obtenerPorEstudiante(estudianteId));
    }
    
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Calificacion>> obtenerPorCurso(@PathVariable String cursoId) {
        return ResponseEntity.ok(calificacionService.obtenerPorCurso(cursoId));
    }
    
    @GetMapping("/profesor/{profesorId}")
    public ResponseEntity<List<Calificacion>> obtenerPorProfesor(@PathVariable String profesorId) {
        return ResponseEntity.ok(calificacionService.obtenerPorProfesor(profesorId));
    }
    
    @PostMapping
    public ResponseEntity<Calificacion> crear(@RequestBody Calificacion calificacion) {
        return ResponseEntity.ok(calificacionService.crear(calificacion));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Calificacion> actualizarNotas(@PathVariable String id, @RequestBody Calificacion calificacion) {
        return ResponseEntity.ok(calificacionService.actualizarNotas(id, calificacion));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        calificacionService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}