import java.util.*;

enum EstadoReserva {
    DISPONIBLE,
    RESERVADA,
    OCUPADA,
    CANCELADA
}

class Estudiante {
    private String nombreCompleto;
    private String codigoInstitucional;
    private String programaAcademico;

    public Estudiante(String nombreCompleto, String codigoInstitucional, String programaAcademico) {
        this.nombreCompleto = nombreCompleto;
        this.codigoInstitucional = codigoInstitucional;
        this.programaAcademico = programaAcademico;
    }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getCodigoInstitucional() { return codigoInstitucional; }
    public void setCodigoInstitucional(String codigoInstitucional) { this.codigoInstitucional = codigoInstitucional; }

    public String getProgramaAcademico() { return programaAcademico; }
    public void setProgramaAcademico(String programaAcademico) { this.programaAcademico = programaAcademico; }

    public void registrarNombreCompleto() {}
    public void registrarCodigoInstitucional() {}
    public void registrarProgramaAcademico() {}

    @Override
    public String toString() {
        return "Estudiante{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", codigoInstitucional='" + codigoInstitucional + '\'' +
                ", programaAcademico='" + programaAcademico + '\'' +
                '}';
    }
}

class Sala {
    private String numeroSala;
    private int capacidadMaxima;
    private boolean disponible;
    private List<Estudiante> estudiante;

    public Sala(String numeroSala, int capacidadMaxima, boolean disponible, List<Estudiante> estudiante) {
        this.numeroSala = numeroSala;
        this.capacidadMaxima = capacidadMaxima;
        this.disponible = disponible;
        this.estudiante = estudiante;
    }

    public String getNumeroSala() { return numeroSala; }
    public void setNumeroSala(String numeroSala) { this.numeroSala = numeroSala; }

    public int getCapacidadMaxima() { return capacidadMaxima; }
    public void setCapacidadMaxima(int capacidadMaxima) { this.capacidadMaxima = capacidadMaxima; }

    public boolean getDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    public List<Estudiante> getEstudiante() { return estudiante; }
    public void setEstudiante(List<Estudiante> estudiante) { this.estudiante = estudiante; }

    public void registrarNumeroSala() {}
    public void registrarCapacidad() {}
    public void registrarDisponibilidad() {}

    @Override
    public String toString() {
        return "Sala{" +
                "numeroSala='" + numeroSala + '\'' +
                ", capacidadMaxima=" + capacidadMaxima +
                ", disponible=" + disponible +
                ", estudiante=" + estudiante +
                '}';
    }
}

class ControlReserva {
    private String fecha;
    private String hora;
    private EstadoReserva estado;

    public ControlReserva() {}

    public ControlReserva(String fecha, String hora, EstadoReserva estado) {
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public EstadoReserva getEstado() { return estado; }
    public void setEstado(EstadoReserva estado) { this.estado = estado; }

    public void registrarFecha() {}
    public void registrarHora() {}
    public void registrarEstado() {}

    @Override
    public String toString() {
        return "ControlReserva{" +
                "fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", estado=" + estado +
                '}';
    }
}

class Biblioteca {
    private List<Estudiante> estudiante;
    private List<Sala> sala;
    private List<ControlReserva> controlReserva;

    public Biblioteca(List<Estudiante> estudiante, List<Sala> sala, List<ControlReserva> controlReserva) {
        this.estudiante = estudiante;
        this.sala = sala;
        this.controlReserva = controlReserva;
    }

    public void registrarEstudiante() {}
    public void registrarSala() {}
    public void registrarControlReserva() {}

    public String consultarHistorial() {
        return "Historial de reservas";
    }

    public String resumenSala() {
        return "Resumen de salas registradas";
    }

    public List<Estudiante> getEstudiante() { return estudiante; }
    public void setEstudiante(List<Estudiante> estudiante) { this.estudiante = estudiante; }

    public List<Sala> getSala() { return sala; }
    public void setSala(List<Sala> sala) { this.sala = sala; }

    public List<ControlReserva> getControlReserva() { return controlReserva; }
    public void setControlReserva(List<ControlReserva> controlReserva) { this.controlReserva = controlReserva; }

    @Override
    public String toString() {
        return "Biblioteca{" +
                "estudiante=" + estudiante +
                ", sala=" + sala +
                ", controlReserva=" + controlReserva +
                '}';
    }
}

public class SistemaDeReservas {
    public static void main(String[] args) {
        Estudiante e1 = new Estudiante("Carlos Pérez", "2025123", "Ingeniería de Software");
        List<Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(e1);

        Sala sala1 = new Sala("S1", 10, true, estudiantes);
        List<Sala> salas = new ArrayList<>();
        salas.add(sala1);

        ControlReserva reserva = new ControlReserva("2025-10-08", "10:00", EstadoReserva.RESERVADA);
        List<ControlReserva> reservas = new ArrayList<>();
        reservas.add(reserva);

        Biblioteca biblioteca = new Biblioteca(estudiantes, salas, reservas);

        System.out.println(biblioteca);
    }
}

