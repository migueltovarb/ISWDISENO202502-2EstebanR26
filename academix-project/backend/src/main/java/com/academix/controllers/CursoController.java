package com.academix.controllers;

import com.academix.models.Curso;
import com.academix.services.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CursoController {
    
    private final CursoService cursoService;
    
    @PostMapping
    public ResponseEntity<Curso> crear(@RequestBody Curso curso) {
        return ResponseEntity.ok(cursoService.crear(curso));
    }
    
    @GetMapping
    public ResponseEntity<List<Curso>> listar() {
        return ResponseEntity.ok(cursoService.listarActivos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarPorId(@PathVariable String id) {
        return cursoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/carrera/{carrera}")
    public ResponseEntity<List<Curso>> buscarPorCarrera(@PathVariable String carrera) {
        return ResponseEntity.ok(cursoService.listarPorCarrera(carrera));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizar(@PathVariable String id, @RequestBody Curso curso) {
        curso.setId(id);
        return ResponseEntity.ok(cursoService.actualizar(curso));
    }
}
