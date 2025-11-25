package com.academix.services;

import com.academix.models.Curso;
import com.academix.repositories.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CursoService {
    
    private final CursoRepository cursoRepository;
    
    public Curso crear(Curso curso) {
        curso.setCupoDisponible(curso.getCupoMaximo());
        return cursoRepository.save(curso);
    }
    
    public Optional<Curso> buscarPorId(String id) {
        return cursoRepository.findById(id);
    }
    
    public List<Curso> listarActivos() {
        return cursoRepository.findByActivoTrue();
    }
    
    public List<Curso> listarPorCarrera(String carrera) {
        return cursoRepository.findByCarrera(carrera);
    }
    
    public Curso actualizar(Curso curso) {
        return cursoRepository.save(curso);
    }
    
    public boolean decrementarCupo(String cursoId) {
        Optional<Curso> cursoOpt = cursoRepository.findById(cursoId);
        if (cursoOpt.isPresent()) {
            Curso curso = cursoOpt.get();
            if (curso.getCupoDisponible() > 0) {
                curso.setCupoDisponible(curso.getCupoDisponible() - 1);
                cursoRepository.save(curso);
                return true;
            }
        }
        return false;
    }
    
    public void incrementarCupo(String cursoId) {
        Optional<Curso> cursoOpt = cursoRepository.findById(cursoId);
        if (cursoOpt.isPresent()) {
            Curso curso = cursoOpt.get();
            if (curso.getCupoDisponible() < curso.getCupoMaximo()) {
                curso.setCupoDisponible(curso.getCupoDisponible() + 1);
                cursoRepository.save(curso);
            }
        }
    }
}
