package entity;

/**
 *
 * @author Kevscl
 */
public class Devolucion {
    private int idDevolucion;
    private int idVenta;
    private int idProducto;
    private int cantidadDevuelta;
    private String fechaDevuelta;
    private String motivo;

    public Devolucion() {
    }

    public Devolucion(int idDevolucion, int idVenta, int idProducto, int cantidadDevuelta, String fechaDevuelta, String motivo) {
        this.idDevolucion = idDevolucion;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidadDevuelta = cantidadDevuelta;
        this.fechaDevuelta = fechaDevuelta;
        this.motivo = motivo;
    }

    public int getIdDevolucion() {
        return idDevolucion;
    }

    public void setIdDevolucion(int idDevolucion) {
        this.idDevolucion = idDevolucion;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidadDevuelta() {
        return cantidadDevuelta;
    }

    public void setCantidadDevuelta(int cantidadDevuelta) {
        this.cantidadDevuelta = cantidadDevuelta;
    }

    public String getFechaDevuelta() {
        return fechaDevuelta;
    }

    public void setFechaDevuelta(String fechaDevuelta) {
        this.fechaDevuelta = fechaDevuelta;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "Devolucion{" + "idDevolucion=" + idDevolucion + ", idVenta=" + idVenta + ", idProducto=" + idProducto + ", cantidadDevuelta=" + cantidadDevuelta + ", fechaDevuelta=" + fechaDevuelta + ", motivo=" + motivo + '}';
    }
    
}
