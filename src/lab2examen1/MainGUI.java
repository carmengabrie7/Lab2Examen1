/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab2examen1;

import java.util.ArrayList;
import javax.swing.SwingUtilities;

/**
 *
 * @author DELL
 */
public class MainGUI {
    private static ArrayList<RentItem> items = new ArrayList<>();
    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            new VentanaMenuPrincipal().setVisible(true);
        });
        
    }
    
}
