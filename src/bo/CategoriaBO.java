package bo;

import bd.Conexion;
import dao.CategoriaDAO;
import entity.Categoria;
import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.JTable;
/**
 *
 * @author Kevscl
 */
public class CategoriaBO {
private static String mensaje = "";
    private CategoriaDAO cdao = new CategoriaDAO();

    public String agregarCategoria(Categoria c) {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = cdao.agregarCategoria(conn, c);
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

    public String actualizarCategoria(Categoria c) {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = cdao.actualizarCategoria(conn, c);
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

    public String eliminarCategoria(int id) {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = cdao.eliminarCategoria(conn, id);
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

    public void listarCategoria(JTable tabla) {
        Connection conn = Conexion.getConnection();
        cdao.listarCategorias(conn, tabla);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void listarCategoriaBusqueda(JTable tabla, String valorBusqueda) {
        Connection conn = Conexion.getConnection();
        cdao.listarCategoriasBusqueda(conn, tabla, valorBusqueda);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String eliminarTodosLasCategorias() {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = cdao.eliminarTodasCategorias(conn);
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
    public void llenaCombo(JComboBox combo) {
        Connection conn = Conexion.getConnection();
        cdao.llenaCombo(conn, combo);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

