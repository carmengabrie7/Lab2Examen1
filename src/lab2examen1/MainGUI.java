package lab2examen1;

import java.util.ArrayList;
import javax.swing.SwingUtilities;

public class MainGUI {

    protected static ArrayList<RentItem> items = new ArrayList<>();

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new VentanaMenuPrincipal(items).setVisible(true);
        });

    }
}

