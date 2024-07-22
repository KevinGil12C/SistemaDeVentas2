package entity;

public class DetalleVenta {
    private int idDetalleVenta;
    private int idProducto;
    private int idVenta;
    private int cantidad;
    private float pUnitario;
    private float total;

    public DetalleVenta() {
    }

    public DetalleVenta(int idDetalleVenta, int idProducto, int idVenta, int cantidad, float pUnitario, float total) {
        this.idDetalleVenta = idDetalleVenta;
        this.idProducto = idProducto;
        this.idVenta = idVenta;
        this.cantidad = cantidad;
        this.pUnitario = pUnitario;
        this.total = total;
    }

    

    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public float getpUnitario() {
        return pUnitario;
    }

    public void setpUnitario(float pUnitario) {
        this.pUnitario = pUnitario;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "DetalleVenta{" +
                "idDetalleVenta=" + idDetalleVenta +
                ", idProducto=" + idProducto +
                ", idVenta=" + idVenta +
                ", pUnitario=" + pUnitario +
                ", total=" + total +
                '}';
    }
}
