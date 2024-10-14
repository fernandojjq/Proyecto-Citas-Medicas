package logica;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

public class GestorCitas {
    private ArrayList<Cita> citas;
    private ArrayList<Doctor> doctores;

    public GestorCitas() {
        citas = new ArrayList<>();
        doctores = new ArrayList<>();
        inicializarDoctores();
    }

    private void inicializarDoctores() {
    	//Nombres y horarios de los doctores trabajan todos los días.
        doctores.add(new Doctor("Dr. Phil McGraw", LocalTime.of(0, 0), LocalTime.of(4, 0)));
        doctores.add(new Doctor("Dr. Gregory House", LocalTime.of(4, 0), LocalTime.of(8, 0)));
        doctores.add(new Doctor("Dra. Meredith Grey", LocalTime.of(8, 0), LocalTime.of(12, 0)));
        doctores.add(new Doctor("Dr. Stephen Strange", LocalTime.of(12, 0), LocalTime.of(16, 0)));
        doctores.add(new Doctor("Dr. John Watson", LocalTime.of(16, 0), LocalTime.of(20, 0)));
        doctores.add(new Doctor("Dr. Leonard McCoy", LocalTime.of(20, 0), LocalTime.of(23, 59)));
    }

    public boolean registrarCita(Paciente paciente, LocalDateTime fechaCita) {
        for (Cita cita : citas) {
            if (cita.getFecha().toLocalDate().equals(fechaCita.toLocalDate())) {
                Duration duracion = Duration.between(cita.getFecha(), fechaCita).abs();
                if (duracion.toMinutes() < 59) {
                    return false;
                }
            }
        }

        Doctor doctorAsignado = asignarDoctor(fechaCita.toLocalTime());
        if (doctorAsignado == null) {
            return false;
        }

        Cita nuevaCita = new Cita(paciente, fechaCita, doctorAsignado);
        citas.add(nuevaCita);
        return true;
    }

    private Doctor asignarDoctor(LocalTime hora) {
        for (Doctor doctor : doctores) {
            if (doctor.estaDisponible(hora)) {
                return doctor;
            }
        }
        return null;
    }

    public ArrayList<Cita> getCitas() {
        return citas;
    }

    public void imprimirCitas() {
        System.out.println("Lista de citas actual:");
        DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        for (Cita cita : citas) {
            System.out.println("Cédula: " + cita.getPaciente().getCedula() + 
                               ", Fecha y hora: " + cita.getFecha().format(formatoFechaHora) +
                               ", Doctor: " + cita.getDoctor().getNombre());
        }
        System.out.println("Fin de la lista de citas.");
    }
}