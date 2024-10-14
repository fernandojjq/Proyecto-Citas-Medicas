package logica;

import java.time.LocalDateTime;

public class Cita {
    private Paciente paciente;
    private LocalDateTime fecha;
    private Doctor doctor;

    public Cita(Paciente paciente, LocalDateTime fecha, Doctor doctor) {
        this.paciente = paciente;
        this.fecha = fecha;
        this.doctor = doctor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}