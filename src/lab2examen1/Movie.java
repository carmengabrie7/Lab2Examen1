package lab2examen1;

import java.util.Calendar;
import javax.swing.ImageIcon;


public class Movie extends RentItem {
    protected Calendar fechaEstreno;
    protected ImageIcon icono;
    
    public Movie (int codigoItem, String nombreItem, double precioBaseRenta, int cantidadCopias){
        super(codigoItem,nombreItem,precioBaseRenta);
        this.fechaEstreno=Calendar.getInstance();
        this.icono=null;
    }
    
    public void setFechaEstreno(Calendar fechaEstreno){
        this.fechaEstreno=fechaEstreno;
    }
    public Calendar getFechaEstreno(){
        return fechaEstreno;
    }
    public void setIcono(ImageIcon icono){
        this.icono=icono;
    }
    public ImageIcon getIcono(){
    return icono;
}

    @Override
    public String toString() {
        return super.toString()+
                "Estado: "+getEstado()+
                "-Movie\n";
    }
    
    public String getEstado(){
      Calendar hoy = Calendar.getInstance();
      Calendar limite = (Calendar) fechaEstreno.clone();
      limite.add(Calendar.MONTH, 3);
      if (!hoy.after(limite)){
          return "ESTRENO";
      } else{
          return "NORMAL";
      }
    }
    
    @Override
    protected double pagoRenta(int dias){
        String estado = getEstado();
        double total = precioBaseRenta;
        
        if (estado.equals("ESTRENO")){
            if (dias>2){
                int extras = dias -2;
                total += extras*50;
            }
        } else {
            if (dias>5){
                int extras = dias -5;
                total += extras*30;
            }     
        }
        return total;
    }
    
    
    
}
