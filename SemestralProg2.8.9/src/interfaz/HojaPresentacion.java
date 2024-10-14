package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HojaPresentacion extends JFrame {
    private static final long serialVersionUID = 1L;

    public HojaPresentacion() {
        setTitle("MedicApp - Presentación");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        // Panel central para la información
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        panelInfo.setBackground(new Color(230, 240, 250));

        // Panel para los logos y el título principal
        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setOpaque(false);

        // Logo FISC (izquierda)
        JLabel lblLogoFisc = new JLabel(new ImageIcon(new ImageIcon(HojaPresentacion.class.getResource("/imagen/logoFisc.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        panelTitulo.add(lblLogoFisc, BorderLayout.WEST);

        // Título principal
        JLabel lblTitulo = new JLabel("UNIVERSIDAD TECNOLÓGICA DE PANAMÁ", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(0, 51, 102));
        panelTitulo.add(lblTitulo, BorderLayout.CENTER);

        // Logo UTP (derecha)
        JLabel lblLogoUTP = new JLabel(new ImageIcon(new ImageIcon(HojaPresentacion.class.getResource("/imagen/logoUTP.jpg")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        panelTitulo.add(lblLogoUTP, BorderLayout.EAST);

        panelInfo.add(panelTitulo);

        addCenteredLabel(panelInfo, "FISC", 20, Font.BOLD, new Color(0, 102, 204));
        addCenteredLabel(panelInfo, "Licenciatura en Ingenieria de Software", 18, Font.PLAIN, Color.BLACK);
        addCenteredLabel(panelInfo, "SEMESTRAL", 18, Font.PLAIN, Color.BLACK);
        addCenteredLabel(panelInfo, "PROGRAMACIÓN DE SOFTWARE", 18, Font.BOLD, new Color(0, 102, 0));
        
        panelInfo.add(Box.createVerticalStrut(20));
        
        addCenteredLabel(panelInfo, "Estudiantes:", 16, Font.BOLD, new Color(153, 0, 0));
        addCenteredLabel(panelInfo, "BRYAN LAW 8-1011-2459", 16, Font.PLAIN, Color.BLACK);
        addCenteredLabel(panelInfo, "FABIAN ATENCIO 3-753-1543", 16, Font.PLAIN, Color.BLACK);
        addCenteredLabel(panelInfo, "ADAN ORTÍZ 8-1024-848", 16, Font.PLAIN, Color.BLACK);
        addCenteredLabel(panelInfo, "FERNANDO JIMÉNEZ 20-24-7669", 16, Font.PLAIN, Color.BLACK);
        
        panelInfo.add(Box.createVerticalStrut(20));
        
        addCenteredLabel(panelInfo, "Profesor: RODRIGO YÁNGÜEZ", 16, Font.BOLD, new Color(0, 51, 102));

        contentPane.add(panelInfo, BorderLayout.CENTER);

        // Panel inferior para los botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelBotones.setBackground(new Color(230, 240, 250));

        JButton btnRegistroCitas = createStyledButton("Registro de Citas");
        btnRegistroCitas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegistroCita registroCita = new RegistroCita();
                registroCita.setVisible(true);
                dispose();
            }
        });
        panelBotones.add(btnRegistroCitas);

        contentPane.add(panelBotones, BorderLayout.SOUTH);
    }

    private void addCenteredLabel(JPanel panel, String text, int fontSize, int fontStyle, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", fontStyle, fontSize));
        label.setForeground(color);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createVerticalStrut(10)); // Reduced vertical spacing
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 12));
        button.setBackground(new Color(240, 240, 240));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        button.setPreferredSize(new Dimension(150, 30));
        return button;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HojaPresentacion frame = new HojaPresentacion();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}