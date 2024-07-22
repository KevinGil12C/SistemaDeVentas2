package dao;

import entity.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CategoriaDAO {

    public String agregarCategoria(Connection con, Categoria categoria) {
        String mensaje;
        PreparedStatement pst = null;
        String sql = "INSERT INTO CATEGORIA(idCategoria, nomCategoria) VALUES(?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, categoria.getIdCategoria());
            pst.setString(2, categoria.getNomCategoria());
            pst.execute();
            pst.close();
            mensaje = "GUARDADO CORRECTAMENTE";
        } catch (SQLException e) {
            mensaje = "ERROR: " + e.getMessage();
        }
        return mensaje;
    }

    public String getMaxID(Connection con) {
        String id = "1"; // Establecer el valor predeterminado como "1"
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT MAX(idCategoria) FROM CATEGORIA";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                String maxID = rs.getString(1);
                if (maxID != null) {
                    id = String.valueOf(Integer.parseInt(maxID) + 1); // Incrementar el valor obtenido de la consulta
                }
            }
        } catch (SQLException e) {
            // Manejar la excepción
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                // Manejar la excepción de cierre de recursos
            }
        }
        return id;
    }

    public String actualizarCategoria(Connection con, Categoria categoria) {
        String mensaje;
        PreparedStatement pst = null;
        String sql = "UPDATE CATEGORIA SET nomCategoria=? WHERE idCategoria=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, categoria.getNomCategoria());
            pst.setInt(2, categoria.getIdCategoria());
            pst.execute();
            pst.close();
            mensaje = "ACTUALIZADO CORRECTAMENTE";
        } catch (SQLException e) {
            mensaje = "ERROR: " + e.getMessage();
        }
        return mensaje;
    }

    public String eliminarCategoria(Connection con, int idCategoria) {
        String mensaje;
        PreparedStatement pst = null;
        String sql = "DELETE FROM CATEGORIA WHERE idCategoria=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCategoria);
            pst.execute();
            pst.close();
            mensaje = "ELIMINADO CORRECTAMENTE";
        } catch (SQLException e) {
            mensaje = "ERROR: " + e.getMessage();
        }
        return mensaje;
    }

    public void listarCategorias(Connection con, JTable tabla) {
        DefaultTableModel model;
        String[] columnas = {"ID", "NOMBRE CATEGORIA"};
        model = new DefaultTableModel(null, columnas);
        String sql = "SELECT * FROM CATEGORIA ORDER BY idCategoria ASC";
        String[] filas = new String[2];
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                filas[0] = rs.getString("idCategoria");
                filas[1] = rs.getString("nomCategoria");
                model.addRow(filas);
            }
            tabla.setModel(model);
        } catch (SQLException e) {
            System.out.println("Error al listar la tabla: " + e.getMessage());
        } finally {
            // Cerrar PreparedStatement y ResultSet
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void listarCategoriasBusqueda(Connection con, JTable tabla, String valorBusqueda) {
        DefaultTableModel model;
        String[] columnas = {"ID", "NOMBRE CATEGORIA"};
        model = new DefaultTableModel(null, columnas);
        String sql = "SELECT * FROM CATEGORIA WHERE idCategoria = ? OR nomCategoria LIKE ? ORDER BY idCategoria ASC";
        String[] filas = new String[2];
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, valorBusqueda);
            pst.setString(2, "%" + valorBusqueda + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                filas[0] = rs.getString("idCategoria");
                filas[1] = rs.getString("nomCategoria");
                model.addRow(filas);
            }
            tabla.setModel(model);
        } catch (SQLException e) {
            System.out.println("Error al listar la tabla: " + e.getMessage());
        } finally {
            // Cerrar PreparedStatement y ResultSet
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String eliminarTodasCategorias(Connection con) {
        String mensaje;
        PreparedStatement pst = null;
        String sql = "DELETE FROM CATEGORIA";
        try {
            pst = con.prepareStatement(sql);
            pst.execute();
            pst.close();
            mensaje = "ELIMINADAS TODAS";
        } catch (SQLException e) {
            mensaje = "ERROR: " + e.getMessage();
        }
        return mensaje;
    }
    public void llenaCombo(Connection con, JComboBox combo) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT nomCategoria FROM CATEGORIA ORDER BY nomCategoria ASC";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                combo.addItem(rs.getString("nomCategoria"));
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println("Error al mostrar id " + e.getMessage());
        }
    }
}
