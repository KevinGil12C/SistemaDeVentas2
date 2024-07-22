package bo;

import bd.Conexion;
import dao.ClienteDAO;
import entity.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Kevscl
 */
public class ClienteBO {

    private static String mensaje = "";
    private ClienteDAO cdao = new ClienteDAO();

    public String agregarCliente(Cliente c) {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = cdao.agregarCliente(conn, c);
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

    public String actualizarCliente(Cliente c) {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = cdao.actualizarCliente(conn, c);
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

    public String eliminarCliente(int id) {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = cdao.eliminarCliente(conn, id);
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

    public void listarCliente(JTable tabla) {
        Connection conn = Conexion.getConnection();
        cdao.listarCliente(conn, tabla);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void listarClienteBusqueda(JTable tabla, String valorBusqueda) {
        Connection conn = Conexion.getConnection();
        cdao.listarClienteBusqueda(conn, tabla, valorBusqueda);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String eliminarTodosLosClientes() {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = cdao.eliminarTodosClientes(conn);
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

    public List<Cliente> obtenerClientes() {
        Connection con = Conexion.getConnection();
        List<String> matchingProductos = new ArrayList<>();
        List<Cliente> clientes = cdao.obtenerClientes(con);
        for (Cliente cl: clientes) {
            matchingProductos.add(cl.getNomCliente());
        }
        return clientes;
    }
    
    public String obtnerCorreoClienteVenta(int id) {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = cdao.obtenerCorreoClientePorVenta(conn, id);
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
