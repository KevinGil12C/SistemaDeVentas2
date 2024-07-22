package bo;

/**
 *
 * @author Kevscl
 */
import bd.Conexion;
import dao.VentaDAO;
import java.sql.*;
import entity.*;
import javax.swing.JTable;

public class VentaBO {

    private static String mensaje = "";
    private VentaDAO vdao = new VentaDAO();

    public String agregarVenta(Venta v, String nomCliente, String user) {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = vdao.agregarVenta(conn, v, nomCliente, user);
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

    public String agregarDetalleVenta(DetalleVenta v, String producto) {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = vdao.agregarDetalleVenta(conn, v, producto);
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
        String id = vdao.getMaxID(conn);
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public void listarVenta(JTable tabla) {
        Connection conn = Conexion.getConnection();
        vdao.listarVentas(conn, tabla);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String listarVentaBusqueda(JTable tabla, String fecha) {
        Connection conn = Conexion.getConnection();
        mensaje = vdao.listarVentasPorFecha(conn, tabla, fecha);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return mensaje;
    }

    public String listarVentaReporte(JTable tabla, String fecha) {
        Connection conn = Conexion.getConnection();
        mensaje = vdao.listarVentasReporte(conn, tabla, fecha);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return mensaje;
    }

    public String listarVentaReporteTotal(JTable tablaa) {
        Connection conn = Conexion.getConnection();
        mensaje = vdao.listarVentasReporteTotal(conn, tablaa);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return mensaje;
    }

    public String listarVentaReporteTotalBusqueda(JTable tablaa, String busqueda) {
        Connection conn = Conexion.getConnection();
        mensaje = vdao.listarVentasReporteTotalBusqueda(conn, tablaa, busqueda);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return mensaje;
    }
}
