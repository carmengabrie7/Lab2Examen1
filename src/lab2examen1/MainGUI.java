/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab2examen1;

import javax.swing.SwingUtilities;

/**
 *
 * @author DELL
 */
public class MainGUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            new VentanaMenuPrincipal().setVisible(true);
        });
        
    }
    
}
