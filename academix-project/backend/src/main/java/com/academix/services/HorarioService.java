package com.academix.services;

import com.academix.models.Horario;
import com.academix.repositories.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HorarioService {
    
    @Autowired
    private HorarioRepository horarioRepository;
    
    public List<Horario> listarTodos() {
        return horarioRepository.findAll();
    }
    
    public Horario obtenerPorId(String id) {
        return horarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Horario no encontrado"));
    }
    
    public List<Horario> obtenerPorCurso(String cursoId) {
        return horarioRepository.findByCursoId(cursoId);
    }
    
    public Horario crear(Horario horario) {
        // Validar que no haya conflicto de horarios en el mismo salón
        return horarioRepository.save(horario);
    }
    
    public Horario actualizar(String id, Horario horarioActualizado) {
        Horario horario = obtenerPorId(id);
        
        if (horarioActualizado.getDiaSemana() != null) {
            horario.setDiaSemana(horarioActualizado.getDiaSemana());
        }
        if (horarioActualizado.getHoraInicio() != null) {
            horario.setHoraInicio(horarioActualizado.getHoraInicio());
        }
        if (horarioActualizado.getHoraFin() != null) {
            horario.setHoraFin(horarioActualizado.getHoraFin());
        }
        if (horarioActualizado.getSalon() != null) {
            horario.setSalon(horarioActualizado.getSalon());
        }
        if (horarioActualizado.getTipo() != null) {
            horario.setTipo(horarioActualizado.getTipo());
        }
        
        return horarioRepository.save(horario);
    }
    
    public void eliminar(String id) {
        horarioRepository.deleteById(id);
    }
}