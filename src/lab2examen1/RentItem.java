/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2examen1;

import javax.swing.ImageIcon;

/**
 *
 * @author eduar
 */
public abstract class RentItem {
    
    protected int codigoItem;
    protected String nombreItem;
    protected double precioBaseRenta;
    protected int cantidadCopias;
    protected ImageIcon imagen;

    public RentItem(int codigoItem, String nombreItem, double precioBaseRenta) {
        this.codigoItem = codigoItem;
        this.nombreItem = nombreItem;
        this.precioBaseRenta = precioBaseRenta;
        this.cantidadCopias=0;
    }

    public int getCodigoItem() {
        return codigoItem;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public double getPrecioBaseRenta() {
        return precioBaseRenta;
    }

    @Override
    public String toString() {
        return "Codigo de Item: "+codigoItem+"\n Nombre de Item: "+nombreItem+"\n Precio Base Renta de Item: "+precioBaseRenta+" Cantidad de copias Disponibles: "+cantidadCopias;
    }
    
    protected abstract double pagoRenta(int dias);
          
    
}
