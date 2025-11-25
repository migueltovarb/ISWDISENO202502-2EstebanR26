package com.academix.controllers;

import com.academix.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private CursoService cursoService;
    
    @Autowired
    private InscripcionService inscripcionService;
    
    @Autowired
    private ProfesorService profesorService;
    
    @Autowired
    private PagoService pagoService;
    
    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> obtenerDashboard() {
        Map<String, Object> dashboard = new HashMap<>();
        
        dashboard.put("totalUsuarios", usuarioService.listarTodos().size());
        dashboard.put("totalEstudiantes", usuarioService.listarPorRol("ESTUDIANTE").size());
        dashboard.put("totalProfesores", profesorService.listarActivos().size());
        dashboard.put("totalCursos", cursoService.listarActivos().size());
        dashboard.put("totalInscripciones", inscripcionService.listarTodas().size());
        
        dashboard.put("pagosPendientes", pagoService.obtenerPorEstado("PENDIENTE").size());
        dashboard.put("pagosCompletados", pagoService.obtenerPorEstado("COMPLETADO").size());
        
        dashboard.put("inscripcionesActivas", inscripcionService.obtenerPorEstado("ACTIVA").size());
        dashboard.put("inscripcionesPendientes", inscripcionService.obtenerPorEstado("PENDIENTE_PAGO").size());
        
        return ResponseEntity.ok(dashboard);
    }
    
    @GetMapping("/reportes/ingresos")
    public ResponseEntity<Map<String, Object>> obtenerReporteIngresos() {
        Map<String, Object> reporte = new HashMap<>();
        
        double ingresoTotal = pagoService.obtenerPorEstado("COMPLETADO")
            .stream()
            .mapToDouble(p -> p.getMonto().doubleValue())
            .sum();
        
        reporte.put("ingresoTotal", ingresoTotal);
        reporte.put("pagosCompletados", pagoService.obtenerPorEstado("COMPLETADO").size());
        
        return ResponseEntity.ok(reporte);
    }
}