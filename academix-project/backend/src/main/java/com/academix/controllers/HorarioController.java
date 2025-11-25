package com.academix.controllers;

import com.academix.models.Horario;
import com.academix.services.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/horarios")
@CrossOrigin(origins = "*")
public class HorarioController {
    
    @Autowired
    private HorarioService horarioService;
    
    @GetMapping
    public ResponseEntity<List<Horario>> listar() {
        return ResponseEntity.ok(horarioService.listarTodos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Horario> obtenerPorId(@PathVariable String id) {
        return ResponseEntity.ok(horarioService.obtenerPorId(id));
    }
    
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Horario>> obtenerPorCurso(@PathVariable String cursoId) {
        return ResponseEntity.ok(horarioService.obtenerPorCurso(cursoId));
    }
    
    @PostMapping
    public ResponseEntity<Horario> crear(@RequestBody Horario horario) {
        return ResponseEntity.ok(horarioService.crear(horario));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Horario> actualizar(@PathVariable String id, @RequestBody Horario horario) {
        return ResponseEntity.ok(horarioService.actualizar(id, horario));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        horarioService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}