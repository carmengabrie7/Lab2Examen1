/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2examen1;

import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
 */
public class Game extends RentItem implements MenuActions {
    
    private Calendar fechaPublicacion;
    private ArrayList<String> especificaciones;
    
    public Game(int codigoItem, String nombreItem, double precioBaseRenta) {
        super(codigoItem, nombreItem, precioBaseRenta);
        fechaPublicacion = Calendar.getInstance();
        especificaciones = new ArrayList<String>();
    }
    
    public void setFechaPublicacion(int year, int mes, int dia) {
        fechaPublicacion.set(Calendar.YEAR, year);
        fechaPublicacion.set(Calendar.MONTH, mes - 1);
        fechaPublicacion.set(Calendar.DAY_OF_MONTH, dia);
    }
    
    public Calendar getFechaPublicacion() {
        return fechaPublicacion;
    }
    
    @Override
    public String toString() {
        int year = fechaPublicacion.get(Calendar.YEAR);
        int month = fechaPublicacion.get(Calendar.MONTH) + 1;
        int day = fechaPublicacion.get(Calendar.DAY_OF_MONTH);

        return super.toString()
                + "\nFecha Publicación: " + day + "/" + month + "/" + year
                + " – PS3 Game";
    }
    
    @Override
    public double pagoRenta(int dias) {
        return 20 * dias;
    }
    
    public void listEspecificaciones() {
        if (especificaciones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay especificaciones aún.");
        } else {
            mostrarEspecificaciones(0);
        }
    }

    private void mostrarEspecificaciones(int i) {
        if (i == especificaciones.size()) {
            return;
        }
        JOptionPane.showMessageDialog(null, (i + 1) + ". " + especificaciones.get(i));
        mostrarEspecificaciones(i + 1);
    }
    
    @Override
    public void submenu() {
        String menu = "SUBMENÚ\n"
                + "1. Actualizar fecha de publicación\n"
                + "2. Agregar especificación\n"
                + "3. Ver especificaciones\n"
                + "4. Salir\n";

        int opcionMenu = Integer.parseInt(JOptionPane.showInputDialog(menu));
        ejecutarOpcion(opcionMenu);
    }

    @Override
    public void ejecutarOpcion(int opcion) {
        switch (opcion) {

            case 1:
                int year = Integer.parseInt(JOptionPane.showInputDialog("Ingrese año:"));
                int month = Integer.parseInt(JOptionPane.showInputDialog("Ingrese mes:"));
                int day = Integer.parseInt(JOptionPane.showInputDialog("Ingrese día:"));
                setFechaPublicacion(year, month, day);
                JOptionPane.showMessageDialog(null, "Fecha actualizada.");
                break;

            case 2:
                String nuevaEspecificacion = JOptionPane.showInputDialog("Escriba la especificación:");
                if (nuevaEspecificacion != null && !nuevaEspecificacion.isEmpty()) {
                    especificaciones.add(nuevaEspecificacion);
                }
                break;

            case 3:
                listEspecificaciones();
                break;

            default:
                JOptionPane.showMessageDialog(null, "Saliendo del submenú...");
                break;
        }
    }
}
