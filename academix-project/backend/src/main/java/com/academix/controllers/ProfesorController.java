package com.academix.controllers;

import com.academix.models.Profesor;
import com.academix.services.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/profesores")
@CrossOrigin(origins = "*")
public class ProfesorController {
    
    @Autowired
    private ProfesorService profesorService;
    
    @GetMapping
    public ResponseEntity<List<Profesor>> listar() {
        return ResponseEntity.ok(profesorService.listarActivos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Profesor> obtenerPorId(@PathVariable String id) {
        return ResponseEntity.ok(profesorService.obtenerPorId(id));
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<Profesor> obtenerPorEmail(@PathVariable String email) {
        Profesor profesor = profesorService.obtenerPorEmail(email);
        if (profesor != null) {
            return ResponseEntity.ok(profesor);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/departamento/{departamento}")
    public ResponseEntity<List<Profesor>> listarPorDepartamento(@PathVariable String departamento) {
        return ResponseEntity.ok(profesorService.listarPorDepartamento(departamento));
    }
    
    @PostMapping
    public ResponseEntity<Profesor> crear(@RequestBody Profesor profesor) {
        return ResponseEntity.ok(profesorService.crear(profesor));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Profesor> actualizar(@PathVariable String id, @RequestBody Profesor profesor) {
        return ResponseEntity.ok(profesorService.actualizar(id, profesor));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        profesorService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}