package bo;

/**
 *
 * @author Kevscl
 */
import bd.Conexion;
import dao.*;
import java.sql.*;
import entity.*;
import javax.swing.JTable;

public class DevolucionBO {

    private static String mensaje = "";
    private DevolucionDAO ddao = new DevolucionDAO();

    public String agregarDevolucion(Devolucion d, String producto) {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = ddao.agregarDevolucion(conn, d, producto);
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

    //Para encontrar el idmax
    public String getMaxID() {
        Connection conn = Conexion.getConnection();
        String id = ddao.getMaxID(conn);
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }
    public String listarDevoluciones(JTable tabla) {
        Connection conn = Conexion.getConnection();
        mensaje = ddao.listarDevolucion(conn, tabla);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return mensaje;
    }
    
    public String listarDevolucionBusqueda(JTable tabla, String aux) {
        Connection conn = Conexion.getConnection();
        mensaje = ddao.listarDevolucionBusqueda(conn, tabla, aux);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return mensaje;
    }
    
    public String listarDevolucionesFecha(JTable tabla, String fecha) {
        Connection conn = Conexion.getConnection();
        mensaje = ddao.listarDevolucionFecha(conn, tabla, fecha);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return mensaje;
    }
}
