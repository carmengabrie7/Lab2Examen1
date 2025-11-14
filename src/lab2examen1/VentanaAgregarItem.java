package lab2examen1;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

public class VentanaAgregarItem extends JFrame {

    private JTextField txtCodigo, txtNombre, txtPrecio, txtCopias;
    private JLabel lblImagen;
    private ImageIcon imagenSeleccionada;
    private ArrayList<RentItem> items;
    private boolean esMovie;
    private VentanaMenuPrincipal menu;


    public VentanaAgregarItem(ArrayList<RentItem> items, VentanaMenuPrincipal menu) {
    this.items = items;
    this.menu = menu;
    preguntarTipo();
    initUI();
}

    private void preguntarTipo() {
        String[] op = {"Movie", "Game"};
        int resp = JOptionPane.showOptionDialog(
                null,
                "¿Qué tipo de ítem desea agregar?",
                "Tipo de Ítem",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                op,
                op[0]
        );
        esMovie = resp == 0;
    }

    private void initUI() {

        setTitle("Agregar Nuevo Ítem");
        setSize(900, 500);
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

        // PANEL CENTRAL FORMULARIO
        JPanel panelForm = new JPanel(new GridLayout(6, 2, 15, 15));
        panelForm.setOpaque(false);

        panelForm.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        panelForm.add(txtCodigo);

        panelForm.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelForm.add(txtNombre);

        panelForm.add(new JLabel("Precio Base:"));
        txtPrecio = new JTextField();
        panelForm.add(txtPrecio);

        panelForm.add(new JLabel("Cantidad de Copias:"));
        txtCopias = new JTextField();
        panelForm.add(txtCopias);

        panelForm.add(new JLabel("Imagen:"));
        JButton btnImagen = new JButton("Cargar Imagen");
        panelForm.add(btnImagen);

        // Preview
        lblImagen = new JLabel();
        lblImagen.setPreferredSize(new Dimension(200, 200));
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);

        // BOTÓN GUARDAR
        JButton btnGuardar = new JButton("Guardar Ítem");
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
    menu.setVisible(true); // volver a mostrar el menú
    dispose();             // cerrar la ventana actual
});


JPanel panelSur = new JPanel();
panelSur.setOpaque(false);
panelSur.add(btnGuardar);
panelSur.add(btnVolver);

fondo.add(panelSur, BorderLayout.SOUTH);


        fondo.add(panelForm, BorderLayout.CENTER);
        fondo.add(lblImagen, BorderLayout.EAST);

        // ACCIONES --------------------------------------

        btnImagen.addActionListener(e -> cargarImagen());

        btnGuardar.addActionListener(e -> guardarItem());
    }
   
    private void cargarImagen() {
        JFileChooser fc = new JFileChooser();
        int r = fc.showOpenDialog(this);

        if (r == JFileChooser.APPROVE_OPTION) {
            File archivo = fc.getSelectedFile();
            imagenSeleccionada = new ImageIcon(archivo.getAbsolutePath());

            Image img = imagenSeleccionada.getImage().getScaledInstance(
                    180, 180, Image.SCALE_SMOOTH
            );
            lblImagen.setIcon(new ImageIcon(img));
        }
    }

    private void guardarItem() {
    try {
        int codigo = Integer.parseInt(txtCodigo.getText());
        String nombre = txtNombre.getText();
        double precio = Double.parseDouble(txtPrecio.getText());
        int copias = Integer.parseInt(txtCopias.getText());

        if (imagenSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Debe cargar una imagen.");
            return;
        }

        // VALIDAR CODIGO ÚNICO
        for (RentItem r : items) {
            if (r.getCodigoItem() == codigo) {
                JOptionPane.showMessageDialog(this, "Código ya existe.");
                return;
            }
        }

        // CREAR OBJETO Y GUARDAR EN ARRAYLIST
        if (esMovie) {
            Movie m = new Movie(codigo, nombre, precio, copias);
            m.setIcono(imagenSeleccionada);
            items.add(m);

        } else {
            Game g = new Game(codigo, nombre, precio);
            g.imagen = imagenSeleccionada;
            items.add(g);
        }

        JOptionPane.showMessageDialog(this, "Ítem agregado correctamente.");

        // ⭐ VOLVER AL MENÚ PRINCIPAL
        menu.setVisible(true);

        // Cerrar esta ventana
        dispose();

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Datos inválidos.");
    }
}

}
