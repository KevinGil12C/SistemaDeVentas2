package entity;

import java.util.Date;

public class Compras {
    private int idCompra;
    private String descripcion;
    private String fecha;
    private float total;
    

    public Compras() {
    }

    public Compras(int idCompra, String descripcion, String fecha, float total) {
        this.idCompra = idCompra;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.total = total;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Compras{" + "idCompra=" + idCompra + ", descripcion=" + descripcion + ", fecha=" + fecha + ", total=" + total + '}';
    }

   
}
