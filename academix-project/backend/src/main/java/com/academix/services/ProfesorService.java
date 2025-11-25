package com.academix.services;

import com.academix.models.Profesor;
import com.academix.repositories.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProfesorService {
    
    @Autowired
    private ProfesorRepository profesorRepository;
    
    public List<Profesor> listarTodos() {
        return profesorRepository.findAll();
    }
    
    public List<Profesor> listarActivos() {
        return profesorRepository.findByActivoTrue();
    }
    
    public Profesor obtenerPorId(String id) {
        return profesorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
    }
    
    public Profesor obtenerPorEmail(String email) {
        return profesorRepository.findByEmail(email).orElse(null);
    }
    
    public List<Profesor> listarPorDepartamento(String departamento) {
        return profesorRepository.findByDepartamento(departamento);
    }
    
    public Profesor crear(Profesor profesor) {
        if (obtenerPorEmail(profesor.getEmail()) != null) {
            throw new RuntimeException("Ya existe un profesor con este email");
        }
        profesor.setActivo(true);
        profesor.setFechaContratacion(LocalDateTime.now());
        profesor.setFechaActualizacion(LocalDateTime.now());
        return profesorRepository.save(profesor);
    }
    
    public Profesor actualizar(String id, Profesor profesorActualizado) {
        Profesor profesor = obtenerPorId(id);
        profesor.setNombre(profesorActualizado.getNombre());
        profesor.setApellido(profesorActualizado.getApellido());
        profesor.setTelefono(profesorActualizado.getTelefono());
        profesor.setEspecialidad(profesorActualizado.getEspecialidad());
        profesor.setDepartamento(profesorActualizado.getDepartamento());
        profesor.setGradoAcademico(profesorActualizado.getGradoAcademico());
        profesor.setFechaActualizacion(LocalDateTime.now());
        return profesorRepository.save(profesor);
    }
    
    public void eliminar(String id) {
        Profesor profesor = obtenerPorId(id);
        profesor.setActivo(false);
        profesorRepository.save(profesor);
    }
}