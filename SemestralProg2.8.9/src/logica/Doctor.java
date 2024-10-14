package logica;

import java.time.LocalTime;

public class Doctor {
    private String nombre;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public Doctor(String nombre, LocalTime horaInicio, LocalTime horaFin) {
        this.nombre = nombre;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public boolean estaDisponible(LocalTime hora) {
        return !hora.isBefore(horaInicio) && !hora.isAfter(horaFin);
    }
}