package bo;

/**
 *
 * @author Kevscl
 */
import bd.Conexion;
import java.sql.*;
import dao.*;
public class RecuperaDatosReportesBO {
    // Método para obtener el número de tipos de productos
    RecuperaDatosReportesDAO r = new RecuperaDatosReportesDAO();
    public int obtenerNumeroTiposDeProductos() {
        Connection conn = Conexion.getConnection();
        int totalTipos = 0;
        try {
            totalTipos = r.contarTiposDeProductos(conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return totalTipos;
    }

    // Método para obtener el número total de ventas
    public int obtenerNumeroVentas() {
        Connection conn = Conexion.getConnection();
        int totalVentas = 0;
        try {
            totalVentas = r.contarVentas(conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return totalVentas;
    }
    
    public float obtenerTotalVentas() {
        Connection conn = Conexion.getConnection();
        float totalVentas = 0.0f;
        try {
            totalVentas = r.obtenerTotalVentas(conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return totalVentas;
    }


    // Método para obtener el total de compras
    public float obtenerTotalCompras() {
        Connection conn = Conexion.getConnection();
        float totalCompras = 0.0f;
        try {
            totalCompras = r.obtenerTotalCompras(conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return totalCompras;
    }

    // Método para obtener el número de pagos pendientes
    public int obtenerNumeroPagosPendientes() {
        Connection conn = Conexion.getConnection();
        int totalPagosPendientes = 0;
        try {
            totalPagosPendientes = r.contarPagosPendientes(conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return totalPagosPendientes;
    }

}
