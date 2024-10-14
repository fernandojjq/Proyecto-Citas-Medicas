package interfaz;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import logica.GestorCitas;
import logica.Cita;

public class TablaCitas extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable tablaCitas;
    private DefaultTableModel modeloTabla;
    private GestorCitas gestorCitas;
    private DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

    public TablaCitas(GestorCitas gestorCitas) {
        this.gestorCitas = gestorCitas;
        setTitle("MedicApp - Tabla de Citas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        String[] columnas = {"Nombre", "Cédula", "Teléfono", "Dirección", "Fecha de Nacimiento", "Género",
                "Fecha de Cita", "Hora de Cita", "Doctor Asignado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaCitas = new JTable(modeloTabla);

        tablaCitas.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaCitas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tablaCitas.setRowHeight(25);
        tablaCitas.setSelectionBackground(new Color(184, 207, 229));
        tablaCitas.setSelectionForeground(Color.BLACK);
        tablaCitas.setGridColor(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(tablaCitas);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.setBackground(new Color(230, 240, 250));

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panelBotones.add(btnRegresar);

        contentPane.add(panelBotones, BorderLayout.SOUTH);

        actualizarTabla();
    }

    public void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (Cita cita : gestorCitas.getCitas()) {
            modeloTabla.addRow(new Object[]{
                    cita.getPaciente().getNombre(),
                    cita.getPaciente().getCedula(),
                    cita.getPaciente().getTelefono(),
                    cita.getPaciente().getDireccion(),
                    cita.getPaciente().getFechaNacimiento().format(formatoFecha),
                    cita.getPaciente().getGenero(),
                    cita.getFecha().toLocalDate().format(formatoFecha),
                    cita.getFecha().toLocalTime().format(formatoHora),
                    cita.getDoctor().getNombre()
            });
        }
    }
}