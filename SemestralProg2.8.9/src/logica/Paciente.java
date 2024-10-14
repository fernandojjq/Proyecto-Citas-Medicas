package logica;

import java.time.LocalDate;

public class Paciente {
    private String nombre;
    private String cedula;
    private String telefono;
    private String direccion;
    private LocalDate fechaNacimiento;
    private String genero;

    public Paciente(String nombre, String cedula, String telefono, String direccion, LocalDate fechaNacimiento, String genero) {
        this.setNombre(nombre);
        this.setCedula(cedula);
        this.setTelefono(telefono);
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        // Elimina espacios extra y convierte a título
        String[] palabras = nombre.trim().toLowerCase().split("\\s+");
        StringBuilder nombreTitulo = new StringBuilder();
        for (String palabra : palabras) {
            if (palabra.length() > 0) {
                nombreTitulo.append(Character.toUpperCase(palabra.charAt(0)))
                            .append(palabra.substring(1)).append(" ");
            }
        }
        this.nombre = nombreTitulo.toString().trim();
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}