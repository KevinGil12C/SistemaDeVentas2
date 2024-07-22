package bo;

/**
 *
 * @author Kevscl
 */
import bd.Conexion;
import dao.*;
import entity.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

public class ProductoBO {

    private static String mensaje = "";
    private ProductoDAO pdao = new ProductoDAO();

    public String agregarProducto(Producto p, String cat) {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = pdao.agregarProducto(conn, p, cat);
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

    public String actualizarProducto(Producto p, String cat) {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = pdao.actualizarProducto(conn, p, cat);
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

    public String eliminarProducto(int id) {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = pdao.eliminarProducto(conn, id);
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
        String id = pdao.getMaxID(conn);
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public void listarProducto(JTable tabla) {
        Connection conn = Conexion.getConnection();
        pdao.listarProducto(conn, tabla);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void listarProductoBusqueda(JTable tabla, String valorBusqueda) {
        Connection conn = Conexion.getConnection();
        pdao.listarProductoBusqueda(conn, tabla, valorBusqueda);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String eliminarTodosLosProductos() {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = pdao.eliminarTodosLosProductos(conn);
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
    public List<Producto> getMatchingProductos(){
        Connection con = Conexion.getConnection();
        List<String> matchingProductos = new ArrayList<>();
        List<Producto> productos = pdao.findProducto(con);
        for(Producto pro: productos){
            matchingProductos.add(pro.getNomProducto());
        }
        return productos;
    }
    
}
