package entity;

public class Venta {

    private int idVenta;
    private int idEmpleado;
    private int idCliente;
    private String fecha;
    private String mPago;
    private float total;

    public Venta() {
    }

    public Venta(int idVenta, int idEmpleado, int idCliente, String fecha, String mPago, float total) {
        this.idVenta = idVenta;
        this.idEmpleado = idEmpleado;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.mPago = mPago;
        this.total = total;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getmPago() {
        return mPago;
    }

    public void setmPago(String mPago) {
        this.mPago = mPago;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Venta{" + "idVenta=" + idVenta + ", idEmpleado=" + idEmpleado + ", idCliente=" + idCliente + ", fecha=" + fecha + ", mPago=" + mPago + ", total=" + total + '}';
    }

   
}
