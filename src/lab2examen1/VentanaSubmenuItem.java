package lab2examen1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentanaSubmenuItem extends JFrame {

    private ArrayList<RentItem> items;
    private VentanaMenuPrincipal menu;
    private JTextField txtCodigo;
    private RentItem itemSeleccionado;

    public VentanaSubmenuItem(ArrayList<RentItem> items, VentanaMenuPrincipal menu) {
        this.items = items;
        this.menu = menu;
        initUI();
    }

    private void initUI() {

        setTitle("Submenú de Ítem");
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
        fondo.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));
        setContentPane(fondo);

        JPanel panelArriba = new JPanel(new GridLayout(1, 3, 15, 15));
        panelArriba.setOpaque(false);

        panelArriba.add(new JLabel("Código del Ítem:"));
        txtCodigo = new JTextField();
        panelArriba.add(txtCodigo);

        JButton btnBuscar = new JButton("Buscar");
        panelArriba.add(btnBuscar);

        fondo.add(panelArriba, BorderLayout.NORTH);

        // Panel centro: opciones de submenú
        JPanel panelOpciones = new JPanel(new GridLayout(4, 1, 10, 10));
        panelOpciones.setOpaque(false);

        JButton btnOpcion1 = new JButton("Actualizar Fecha de Publicación");
        JButton btnOpcion2 = new JButton("Agregar Especificación");
        JButton btnOpcion3 = new JButton("Ver Especificaciones");
        JButton btnSalir = new JButton("Salir del Submenú");

        panelOpciones.add(btnOpcion1);
        panelOpciones.add(btnOpcion2);
        panelOpciones.add(btnOpcion3);
        panelOpciones.add(btnSalir);

        fondo.add(panelOpciones, BorderLayout.CENTER);

        JButton btnVolver = new JButton("Volver");
        JPanel panelSur = new JPanel();
        panelSur.setOpaque(false);
        panelSur.add(btnVolver);
        fondo.add(panelSur, BorderLayout.SOUTH);


        btnBuscar.addActionListener(e -> buscarItem());

        btnOpcion1.addActionListener(e -> ejecutarSiValido(1));
        btnOpcion2.addActionListener(e -> ejecutarSiValido(2));
        btnOpcion3.addActionListener(e -> ejecutarSiValido(3));
        btnSalir.addActionListener(e -> ejecutarSiValido(4));

        btnVolver.addActionListener(e -> {
            menu.setVisible(true);
            dispose();
        });
    }

    private void buscarItem() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText());
            itemSeleccionado = null;

            for (RentItem r : items) {
                if (r.getCodigoItem() == codigo) {
                    itemSeleccionado = r;
                    break;
                }
            }

            if (itemSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Item No Existe");
                return;
            }

            if (!(itemSeleccionado instanceof MenuActions)) {
                JOptionPane.showMessageDialog(this, "Este ítem no tiene submenú.");
                itemSeleccionado = null;
                return;
            }

            JOptionPane.showMessageDialog(this, "Ítem encontrado. Puede seleccionar una opción.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Código inválido.");
        }
    }

    private void ejecutarSiValido(int opcion) {
        if (itemSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe buscar un ítem primero.");
            return;
        }

        MenuActions accion = (MenuActions) itemSeleccionado;
        accion.ejecutarOpcion(opcion);

        if (opcion == 4) {
            menu.setVisible(true);
            dispose();
        }
    }
}

