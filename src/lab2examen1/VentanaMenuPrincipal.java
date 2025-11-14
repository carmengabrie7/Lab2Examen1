/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2examen1;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author eduar
 */
public class VentanaMenuPrincipal extends JFrame {
    
    private JButton btnAgregar;
    private JButton btnRentar;
    private JButton btnSubmenu;
    private JButton btnImprimir;

    public VentanaMenuPrincipal() {
        initUI();
    }

    private void initUI() {
        setTitle("MediaTrack");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);  // Centrar ventana

        // Panel de fondo
        JPanel fondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Fondo degradado azul claro
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                Color c1 = new Color(209, 240, 255);
                Color c2 = new Color(150, 210, 255);
                GradientPaint gp = new GradientPaint(0, 0, c1, 0, getHeight(), c2);
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        fondo.setLayout(new BorderLayout(20, 20));
        fondo.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));
        setContentPane(fondo);

        // --------- Panel IZQUIERDO: Logo ----------
        JPanel panelLogo = new JPanel();
        panelLogo.setOpaque(false);
        panelLogo.setLayout(new BorderLayout());

        // Carga tu imagen aquí (reemplaza "logo.png" por tu archivo)
        JLabel lblLogo = new JLabel();
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon icon = new ImageIcon("logo.png"); // tu icono de claqueta + control
        lblLogo.setIcon(icon);

        panelLogo.add(lblLogo, BorderLayout.CENTER);
        fondo.add(panelLogo, BorderLayout.WEST);

        // --------- Panel CENTRAL: Título + Botones ----------
        JPanel panelCentro = new JPanel();
        panelCentro.setOpaque(false);
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));

        // Título
        JLabel lblTitulo = new JLabel("MediaTrack");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 48));
        lblTitulo.setForeground(new Color(70, 70, 70));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelCentro.add(lblTitulo);
        panelCentro.add(Box.createVerticalStrut(40));

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setOpaque(false);
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));

        btnAgregar  = crearBoton("Agregar Item");
        btnRentar   = crearBoton("Rentar");
        btnSubmenu  = crearBoton("Submenu");
        btnImprimir = crearBoton("Imprimir");

        panelBotones.add(btnAgregar);
        panelBotones.add(Box.createVerticalStrut(20));
        panelBotones.add(btnRentar);
        panelBotones.add(Box.createVerticalStrut(20));
        panelBotones.add(btnSubmenu);
        panelBotones.add(Box.createVerticalStrut(20));
        panelBotones.add(btnImprimir);

        panelCentro.add(panelBotones);

        fondo.add(panelCentro, BorderLayout.CENTER);

        // Aquí puedes agregar los listeners de cada botón
        // (por ahora solo ejemplos vacíos)
        btnAgregar.addActionListener(e -> System.out.println("Agregar Item"));
        btnRentar.addActionListener(e -> System.out.println("Rentar"));
        btnSubmenu.addActionListener(e -> System.out.println("Submenu"));
        btnImprimir.addActionListener(e -> System.out.println("Imprimir"));
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 26));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(80, 80, 80));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(280, 70));
        btn.setMaximumSize(new Dimension(280, 70));
        return btn;
    }
}
