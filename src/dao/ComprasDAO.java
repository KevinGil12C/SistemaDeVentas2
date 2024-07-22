package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import entity.*;
import java.sql.Date;

public class ComprasDAO {

    private static String mensaje = "";
    private String sql = "";

    public String agregarCompra(Connection con, Compras compra) {
        PreparedStatement pst = null;
        String sql = "INSERT INTO COMPRAS(idCompra, descripcion, fecha, total) VALUES(?, ?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, compra.getIdCompra());
            pst.setString(2, compra.getDescripcion());
            pst.setString(3, compra.getFecha());
            pst.setFloat(4, compra.getTotal());
            pst.execute();
            pst.close();
            return "GUARDADO CORRECTAMENTE";
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }

    public String getMaxID(Connection con) {
        String id = "1"; // Establecer el valor predeterminado como "1"
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT MAX(idCompra) FROM COMPRAS";
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

    public String actualizarCompra(Connection con, Compras compra) {
        PreparedStatement pst = null;
        String sql = "UPDATE COMPRAS SET descripcion=?, fecha=?, total=? WHERE idCOmpra=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, compra.getDescripcion());
            pst.setString(2, compra.getFecha());
            pst.setFloat(3, compra.getTotal());
            pst.setInt(4, compra.getIdCompra());
            pst.execute();
            pst.close();
            return "ACTUALIZADO CORRECTAMENTE";
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }

    public String eliminarCompra(Connection con, int idCompra) {
        PreparedStatement pst = null;
        String sql = "DELETE FROM COMPRAS WHERE idCompra=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCompra);
            pst.execute();
            pst.close();
            return "ELIMINADO CORRECTAMENTE";
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }

    public void listarCompras(Connection con, JTable tabla) {
        DefaultTableModel model;
        String[] columnas = {"ID", "DESCRIPCIÓN", "FECHA", "TOTAL"};
        model = new DefaultTableModel(null, columnas);
        String sql = "SELECT * FROM COMPRAS ORDER BY idCompra ASC";
        String[] filas = new String[4];
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                for (int i = 0; i < 4; i++) {
                    filas[i] = rs.getString(i + 1);
                }
                model.addRow(filas);
            }
            tabla.setModel(model);
        } catch (Exception e) {
            System.out.println("NO SE PUEDE LISTAR LA TABLA");
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

    public void listarComprasBusqueda(Connection con, JTable tabla, String valorBusqueda) {
        DefaultTableModel model;
        String[] columnas = {"ID", "DESCRIPCIÓN", "FECHA", "TOTAL"};
        model = new DefaultTableModel(null, columnas);
        String sql = "SELECT * FROM COMPRAS WHERE idCompra = ? OR descripcion LIKE ? OR fecha LIKE ? OR total LIKE ? ORDER BY idCompra ASC";
        String[] filas = new String[4];
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, valorBusqueda);
            for (int i = 2; i <= 4; i++) {
                pst.setString(i, "%" + valorBusqueda + "%");
            }
            rs = pst.executeQuery();
            while (rs.next()) {
                for (int i = 0; i < 4; i++) {
                    filas[i] = rs.getString(i + 1);
                }
                model.addRow(filas);
            }
            tabla.setModel(model);
        } catch (Exception e) {
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

    public String eliminarTodasLasCompras(Connection con) {
        PreparedStatement pst = null;
        String sql = "DELETE FROM COMPRAS";
        try {
            pst = con.prepareStatement(sql);
            pst.execute();
            pst.close();
            return "ELIMINADAS TODAS LAS COMPRAS";
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }
}
