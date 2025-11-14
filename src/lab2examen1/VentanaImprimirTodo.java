/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2examen1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentanaImprimirTodo extends JFrame {

    public VentanaImprimirTodo(ArrayList<RentItem> items, VentanaMenuPrincipal menu) {

        setTitle("Listado de Ítems Registrados");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel fondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color c1 = new Color(209, 240, 255);
                Color c2 = new Color(150, 210, 255);
                GradientPaint gp = new GradientPaint(0, 0, c1, 0, getHeight(), c2);
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        fondo.setLayout(new BorderLayout(20, 20));
        fondo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(fondo);

        JPanel panelTarjetas = new JPanel();
        panelTarjetas.setLayout(new GridLayout(0, 2, 15, 15));
        panelTarjetas.setOpaque(false);

        // Crear tarjetas para cada ítem
        for (RentItem r : items) {

            JPanel tarjeta = new JPanel(new BorderLayout());
            tarjeta.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 2));
            tarjeta.setBackground(Color.WHITE);

            // Imagen
            JLabel lblImg = new JLabel();
            lblImg.setHorizontalAlignment(JLabel.CENTER);

            ImageIcon img = null;

            if (r instanceof Movie m) {
                img = m.getIcono();
            } else {
                img = r.imagen; // Game usa imagen directamente
            }

            if (img != null) {
                Image scaled = img.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
                lblImg.setIcon(new ImageIcon(scaled));
            } else {
                lblImg.setText("Sin imagen");
            }

            tarjeta.add(lblImg, BorderLayout.NORTH);

            // Info del ítem
            JTextArea info = new JTextArea();
            info.setEditable(false);
            info.setBackground(Color.WHITE);
            info.setFont(new Font("Segoe UI", Font.PLAIN, 14));

            String texto = "";
            texto += "Nombre: " + r.getNombreItem() + "\n";
            texto += "Precio Base: Lps " + r.getPrecioBaseRenta() + "\n";
            texto += "Código: " + r.getCodigoItem() + "\n";

            if (r instanceof Movie m) {
                texto += "Estado: " + m.getEstado() + "\n";
            } else {
                texto += "Tipo: Videojuego\n";
            }

            info.setText(texto);

            tarjeta.add(info, BorderLayout.CENTER);

            // Agregar tarjeta al panel general
            panelTarjetas.add(tarjeta);
        }

        JScrollPane scroll = new JScrollPane(panelTarjetas);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        fondo.add(scroll, BorderLayout.CENTER);

        // Botón VOLVER
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            menu.setVisible(true);
            dispose();
        });

        JPanel panelSur = new JPanel();
        panelSur.setOpaque(false);
        panelSur.add(btnVolver);

        fondo.add(panelSur, BorderLayout.SOUTH);

        setVisible(true);
    }
}

