package lab2examen1;

import java.time.LocalDate;
import java.util.Calendar;


public class Movie extends RentItem {
    protected Calendar fechaEstreno;
    
    public Movie (int codigoItem, String nombreItem, double precioBaseRenta, int cantidadCopias){
        super(codigoItem,nombreItem,precioBaseRenta);
        this.fechaEstreno=Calendar.getInstance();
    }
    
    public void setFechaEstreno(Calendar fechaEstreno){
        this.fechaEstreno=fechaEstreno;
    }
    public Calendar getFechaEstreno(){
        return fechaEstreno;
    }

    @Override
    public String toString() {
        return "Codigo de Item: "+codigoItem+
                "\n Nombre de Item: "+nombreItem+
                "\n Precio Base Renta de Item: "+precioBaseRenta+
                "\n Cantidad de copias Disponibles: "+cantidadCopias+
                "\n ";
    }
    
    public String getEstado(){
      LocalDate hoy = LocalDate.now();
      LocalDate limite = fechasEstreno.plus3months;
    }
    
    @Override
    protected double pagoRenta(int dias){
        
    }
    
    
    
}
