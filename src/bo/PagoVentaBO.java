package bo;

/**
 *
 * @author Kevscl
 */
import bd.Conexion;
import java.sql.*;
import dao.*;
import entity.*;
import javax.swing.JTable;
public class PagoVentaBO {
    private String mensaje="";
    PagoVentaDAO pvdao = new PagoVentaDAO();
    
    public String agregarPagoVenta(PagoVentas pv) {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = pvdao.agregarPagoVenta(conn, pv);
        } catch (Exception e) {
            mensaje = mensaje + " " + e.getMessage();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                mensaje = mensaje + " " + e.getMessage();
            }
        }
        return mensaje;
    }
    public String aabonoPagoVenta(PagoVentas pv) {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = pvdao.abonarPagoVenta(conn, pv);
        } catch (Exception e) {
            mensaje = mensaje + " " + e.getMessage();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                mensaje = mensaje + " " + e.getMessage();
            }
        }
        return mensaje;
    }
    public String getMaxID() {
        Connection conn = Conexion.getConnection();
        String id = pvdao.getMaxID(conn);
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }
    public void listarVentaPago(JTable tabla) {
        Connection conn = Conexion.getConnection();
        pvdao.listarVentaPagos(conn, tabla);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void listarVentaPagoBusqueda(JTable tabla, String busqueda) {
        Connection conn = Conexion.getConnection();
        pvdao.listarVentaPagosBusqueda(conn, tabla, busqueda);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void listarPago(JTable tabla) {
        Connection conn = Conexion.getConnection();
        pvdao.listarPagos(conn, tabla);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
