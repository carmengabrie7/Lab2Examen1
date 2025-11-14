package lab2examen1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentanaRentar extends JFrame {

    private ArrayList<RentItem> items;
    private VentanaMenuPrincipal menu;
    private JTextField txtCodigo, txtDias;
    private JLabel lblDatos, lblImagen;
    private RentItem itemEncontrado;

    public VentanaRentar(ArrayList<RentItem> items, VentanaMenuPrincipal menu) {
        this.items = items;
        this.menu = menu;
        initUI();
    }

    private void initUI() {

        setTitle("Rentar Ítem");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Fondo degradado
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
        fondo.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));
        setContentPane(fondo);

        // Panel superior (formulario)
        JPanel panelForm = new JPanel(new GridLayout(3, 2, 15, 15));
        panelForm.setOpaque(false);

        panelForm.add(new JLabel("Código del Ítem:"));
        txtCodigo = new JTextField();
        panelForm.add(txtCodigo);

        panelForm.add(new JLabel("Días de Renta:"));
        txtDias = new JTextField();
        panelForm.add(txtDias);

        JButton btnBuscar = new JButton("Buscar");
        JButton btnRentar = new JButton("Rentar");

        panelForm.add(btnBuscar);
        panelForm.add(btnRentar);

        fondo.add(panelForm, BorderLayout.NORTH);

        // Panel central
        lblDatos = new JLabel("Información del ítem aparecerá aquí.");
        lblDatos.setVerticalAlignment(SwingConstants.TOP);

        lblImagen = new JLabel();
        lblImagen.setPreferredSize(new Dimension(200, 200));

        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setOpaque(false);
        panelCentro.add(lblDatos, BorderLayout.CENTER);
        panelCentro.add(lblImagen, BorderLayout.EAST);

        fondo.add(panelCentro, BorderLayout.CENTER);

        // Panel inferior
        JButton btnVolver = new JButton("Volver");
        JPanel panelSur = new JPanel();
        panelSur.setOpaque(false);
        panelSur.add(btnVolver);

        fondo.add(panelSur, BorderLayout.SOUTH);

        // ACCIONES ---------------------------------------

        btnBuscar.addActionListener(e -> buscarItem());
        btnRentar.addActionListener(e -> procesarRenta());

        btnVolver.addActionListener(e -> {
            menu.setVisible(true);
            dispose();
        });
    }

    private void buscarItem() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText());
            itemEncontrado = null;

            // Buscar item por código
            for (RentItem r : items) {
                if (r.getCodigoItem() == codigo) {
                    itemEncontrado = r;
                    break;
                }
            }

            if (itemEncontrado == null) {
                JOptionPane.showMessageDialog(this, "Item No Existe");
                lblDatos.setText("No se encontró ningún ítem.");
                lblImagen.setIcon(null);
                return;
            }

            lblDatos.setText("<html>" + itemEncontrado.toString().replace("\n", "<br>") + "</html>");

            ImageIcon img;

            if (itemEncontrado instanceof Movie m) {
                img = m.getIcono();
            } else {
                img = itemEncontrado.imagen;
            }

            if (img != null) {
                Image resized = img.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
                lblImagen.setIcon(new ImageIcon(resized));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Código inválido.");
        }
    }

    private void procesarRenta() {

        if (itemEncontrado == null) {
            JOptionPane.showMessageDialog(this, "Debe buscar un ítem primero.");
            return;
        }

        try {
            int dias = Integer.parseInt(txtDias.getText());
            double total = itemEncontrado.pagoRenta(dias);

            JOptionPane.showMessageDialog(this,
                    "Monto total de la renta: " + total + " Lps",
                    "Total",
                    JOptionPane.INFORMATION_MESSAGE
            );

            menu.setVisible(true);
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Días inválidos.");
        }
    }
}
