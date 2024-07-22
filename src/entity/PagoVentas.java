package entity;

/**
 *
 * @author Kevscl
 */
public class PagoVentas {
    private int idPago;
    private int idVenta;
    private String fechaPago;
    private float abono;
    private String nombreCliente;

    public PagoVentas() {
    }

    public PagoVentas(int idPago, int idVenta, String fechaPago, float abono, String nombreCliente) {
        this.idPago = idPago;
        this.idVenta = idVenta;
        this.fechaPago = fechaPago;
        this.abono = abono;
        this.nombreCliente = nombreCliente;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public float getAbono() {
        return abono;
    }

    public void setAbono(float abono) {
        this.abono = abono;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    @Override
    public String toString() {
        return "PagoVentas{" + "idPago=" + idPago + ", idVenta=" + idVenta + ", fechaPago=" + fechaPago + ", abono=" + abono + ", nombreCliente=" + nombreCliente + '}';
    }
    
}

