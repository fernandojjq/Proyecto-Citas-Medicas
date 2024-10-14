package interfaz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import com.toedter.calendar.JDateChooser;
import logica.*;

public class RegistroCita extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JDateChooser fechaChooserCita, fechaChooserNacimiento;
    private JTextField nombrePaciente, cedulaPaciente, telefonoPaciente;
    private JTextArea direccionPaciente;
    private JComboBox<String> generoPaciente;
    private JSpinner horaCita;
    private GestorCitas gestorCitas;

    public RegistroCita() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 700);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        setTitle("MedicApp - Registro de Citas");

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageIcon fondo = new ImageIcon("fondo.jpg");
        JLabel labelFondo = new JLabel(fondo);
        getContentPane().add(labelFondo, BorderLayout.CENTER);

        gestorCitas = new GestorCitas();

        fechaChooserCita = new JDateChooser();
        fechaChooserNacimiento = new JDateChooser();
        nombrePaciente = new JTextField(30);
        cedulaPaciente = new JTextField(30);
        telefonoPaciente = new JTextField(30);
        direccionPaciente = new JTextArea(5, 30);
        direccionPaciente.setLineWrap(true);
        direccionPaciente.setWrapStyleWord(true);
        JScrollPane scrollDireccion = new JScrollPane(direccionPaciente);
        generoPaciente = new JComboBox<>(new String[] { "Masculino", "Femenino", "Otro" });
        SpinnerDateModel spinnerModel = new SpinnerDateModel();
        spinnerModel.setCalendarField(Calendar.MINUTE);
        horaCita = new JSpinner(spinnerModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(horaCita, "HH:mm");
        horaCita.setEditor(timeEditor);

        JButton botonRegistrar = new JButton("Registrar");
        JButton botonVerTabla = new JButton("Ver Tabla de Citas");

        botonRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarCita();
            }
        });

        botonVerTabla.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TablaCitas tablaCitas = new TablaCitas(gestorCitas);
                tablaCitas.setVisible(true);
            }
        });

        nombrePaciente.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                    e.consume();
                }
            }
        });

        cedulaPaciente.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String text = cedulaPaciente.getText();
                if (Character.isLowerCase(c)) {
                    e.setKeyChar(Character.toUpperCase(c));
                }
                if (!Character.isLetterOrDigit(c) && c != '-') {
                    e.consume();
                }
                if (c == '-' && text.endsWith("-")) {
                    e.consume();
                }
            }
        });

        telefonoPaciente.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String text = telefonoPaciente.getText();
                if (!Character.isDigit(c) && c != '-') {
                    e.consume();
                }
                if (c == '-' && text.endsWith("-")) {
                    e.consume();
                }
                if (text.length() >= 15) {
                    e.consume();
                }
            }
        });

        JPanel panelDatos = new JPanel();
        panelDatos.setOpaque(false);
        GridBagLayout gridBag = new GridBagLayout();
        panelDatos.setLayout(gridBag);
        GridBagConstraints constraints = new GridBagConstraints();

        Font fuenteEtiquetas = new Font("Arial", Font.BOLD, 16);
        Font fuenteTitulo = new Font("Arial", Font.BOLD, 24);
        Color colorEtiquetas = new Color(0, 51, 102);
        Color colorFondo = new Color(230, 240, 250);

        JLabel tituloLabel = new JLabel("MedicApp - Registro de Citas");
        tituloLabel.setFont(fuenteTitulo);
        tituloLabel.setForeground(Color.WHITE);
        tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tituloLabel.setOpaque(true);
        tituloLabel.setBackground(new Color(0, 102, 204));
        getContentPane().add(tituloLabel, BorderLayout.NORTH);

        panelDatos.setBackground(new Color(255, 255, 255));

        JLabel etiquetaNombre = new JLabel("Nombre:");
        etiquetaNombre.setFont(fuenteEtiquetas);
        etiquetaNombre.setForeground(colorEtiquetas);
        addComponent(panelDatos, etiquetaNombre, 0, 0, 1, 1, gridBag, constraints);
        addComponent(panelDatos, nombrePaciente, 1, 0, 1, 1, gridBag, constraints);

        JLabel etiquetaCedula = new JLabel("Cédula:");
        etiquetaCedula.setFont(fuenteEtiquetas);
        etiquetaCedula.setForeground(colorEtiquetas);
        addComponent(panelDatos, etiquetaCedula, 0, 1, 1, 1, gridBag, constraints);
        addComponent(panelDatos, cedulaPaciente, 1, 1, 1, 1, gridBag, constraints);

        JLabel etiquetaTelefono = new JLabel("Teléfono:");
        etiquetaTelefono.setFont(fuenteEtiquetas);
        etiquetaTelefono.setForeground(colorEtiquetas);
        addComponent(panelDatos, etiquetaTelefono, 0, 2, 1, 1, gridBag, constraints);
        addComponent(panelDatos, telefonoPaciente, 1, 2, 1, 1, gridBag, constraints);

        JLabel etiquetaDireccion = new JLabel("Dirección:");
        etiquetaDireccion.setFont(fuenteEtiquetas);
        etiquetaDireccion.setForeground(colorEtiquetas);
        addComponent(panelDatos, etiquetaDireccion, 0, 3, 1, 1, gridBag, constraints);
        addComponent(panelDatos, scrollDireccion, 1, 3, 1, 1, gridBag, constraints);

        JLabel etiquetaFechaNacimiento = new JLabel("Fecha de Nacimiento:");
        etiquetaFechaNacimiento.setFont(fuenteEtiquetas);
        etiquetaFechaNacimiento.setForeground(colorEtiquetas);
        addComponent(panelDatos, etiquetaFechaNacimiento, 0, 4, 1, 1, gridBag, constraints);
        addComponent(panelDatos, fechaChooserNacimiento, 1, 4, 1, 1, gridBag, constraints);
        fechaChooserNacimiento.setPreferredSize(new Dimension(200, 30));

        JLabel etiquetaGenero = new JLabel("Género:");
        etiquetaGenero.setFont(fuenteEtiquetas);
        etiquetaGenero.setForeground(colorEtiquetas);
        addComponent(panelDatos, etiquetaGenero, 0, 5, 1, 1, gridBag, constraints);
        addComponent(panelDatos, generoPaciente, 1, 5, 1, 1, gridBag, constraints);

        JLabel etiquetaFechaCita = new JLabel("Fecha de Cita:");
        etiquetaFechaCita.setFont(fuenteEtiquetas);
        etiquetaFechaCita.setForeground(colorEtiquetas);
        addComponent(panelDatos, etiquetaFechaCita, 0, 6, 1, 1, gridBag, constraints);
        addComponent(panelDatos, fechaChooserCita, 1, 6, 1, 1, gridBag, constraints);
        fechaChooserCita.setPreferredSize(new Dimension(200, 30));

        JLabel etiquetaHoraCita = new JLabel("Hora de Cita:");
        etiquetaHoraCita.setFont(fuenteEtiquetas);
        etiquetaHoraCita.setForeground(colorEtiquetas);
        addComponent(panelDatos, etiquetaHoraCita, 0, 7, 1, 1, gridBag, constraints);
        addComponent(panelDatos, horaCita, 1, 7, 1, 1, gridBag, constraints);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.setBackground(colorFondo);
        panelBotones.add(botonRegistrar);
        panelBotones.add(botonVerTabla);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(panelDatos, BorderLayout.CENTER);
        mainPanel.add(panelBotones, BorderLayout.SOUTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void addComponent(JPanel panel, Component component, int gridx, int gridy, int gridwidth, int gridheight,
            GridBagLayout gridBag, GridBagConstraints constraints) {
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth = gridwidth;
        constraints.gridheight = gridheight;
        constraints.insets = new Insets(10, 10, 10, 10);
        gridBag.setConstraints(component, constraints);
        panel.add(component);
    }

    public void registrarCita() {
        String nombre = nombrePaciente.getText().trim();
        String cedula = cedulaPaciente.getText().trim();
        String telefono = telefonoPaciente.getText().trim();
        String direccion = direccionPaciente.getText().trim();

        if (nombre.isEmpty() || cedula.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validación de cédula
        if (cedula.length() < 6 || cedula.startsWith("-") || cedula.endsWith("-")) {
            JOptionPane.showMessageDialog(this, "La cédula debe tener al menos 6 caracteres y no puede empezar ni terminar con '-'.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validación de teléfono
        if (telefono.length() < 7 || telefono.length() > 15 || telefono.startsWith("-") || telefono.endsWith("-")) {
            JOptionPane.showMessageDialog(this, "El número de teléfono debe tener entre 7 y 15 caracteres y no puede empezar ni terminar con '-'.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Date fechaNacimiento = fechaChooserNacimiento.getDate();
        if (fechaNacimiento == null) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una fecha de nacimiento.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (fechaNacimiento.after(new Date())) {
            JOptionPane.showMessageDialog(this, "La fecha de nacimiento no puede ser mayor a la fecha actual.", "Error",
            		JOptionPane.ERROR_MESSAGE);
            return;
        }

        String genero = (String) generoPaciente.getSelectedItem();

        Date fechaCita = fechaChooserCita.getDate();
        if (fechaCita == null) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una fecha de cita.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Date hora = (Date) horaCita.getValue();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaCita);
        Calendar calHora = Calendar.getInstance();
        calHora.setTime(hora);
        cal.set(Calendar.HOUR_OF_DAY, calHora.get(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, calHora.get(Calendar.MINUTE));
        LocalDateTime fechaHoraCita = cal.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        if (fechaHoraCita.isBefore(LocalDateTime.now())) {
            JOptionPane.showMessageDialog(this, "La fecha de la cita no puede ser en el pasado.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Paciente nuevoPaciente = new Paciente(nombre, cedula, telefono, direccion, 
                fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), genero);
        if (!gestorCitas.registrarCita(nuevoPaciente, fechaHoraCita)) {
            JOptionPane.showMessageDialog(this, 
                "No se pudo registrar la cita. Verifique la disponibilidad del horario o asegúrese de que haya al menos 1 hora de diferencia entre citas del mismo día.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Cita registrada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        limpiarCampos();
    }

    public void limpiarCampos() {
        nombrePaciente.setText("");
        cedulaPaciente.setText("");
        telefonoPaciente.setText("");
        direccionPaciente.setText("");
        fechaChooserNacimiento.setDate(null);
        fechaChooserCita.setDate(null);
        horaCita.setValue(new Date());
    }
}