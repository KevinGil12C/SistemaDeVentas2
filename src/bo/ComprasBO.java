package bo;

/**
 *
 * @author Kevscl
 */
import bd.Conexion;
import dao.*;
import entity.*;
import java.sql.*;
import javax.swing.JTable;

public class ComprasBO {
private static String mensaje = "";
    private ComprasDAO cdao = new ComprasDAO();

    public String agregarCompra(Compras c) {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = cdao.agregarCompra(conn, c);
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

    public String actualizarCompra(Compras c) {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = cdao.actualizarCompra(conn, c);
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

    public String eliminarCompra(int id) {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = cdao.eliminarCompra(conn, id);
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
        String id = cdao.getMaxID(conn);
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public void listarCompra(JTable tabla) {
        Connection conn = Conexion.getConnection();
        cdao.listarCompras(conn, tabla);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void listarCompraBusqueda(JTable tabla, String valorBusqueda) {
        Connection conn = Conexion.getConnection();
        cdao.listarComprasBusqueda(conn, tabla, valorBusqueda);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String eliminarTodasLasCompras() {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = cdao.eliminarTodasLasCompras(conn);
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

}

