package dao;

import entity.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kevscl
 */
public class ProductoDAO {

    public String agregarProducto(Connection con, Producto producto, String nomCategoria) {
        String mensaje;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "INSERT INTO PRODUCTO(idProducto, nomProducto, idCategoria, tam, precio, stock) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            // Obtener el identificador de categoría basado en el nombre de categoría
            String sqlCategoria = "SELECT idCategoria FROM CATEGORIA WHERE nomCategoria = ?";
            pst = con.prepareStatement(sqlCategoria);
            pst.setString(1, nomCategoria);
            rs = pst.executeQuery();
            int idCategoria = -1; // Valor predeterminado para el identificador de categoría
            if (rs.next()) {
                idCategoria = rs.getInt("idCategoria");
            }
            rs.close();
            pst.close();

            if (idCategoria != -1) {
                pst = con.prepareStatement(sql);
                pst.setInt(1, producto.getIdProducto());
                pst.setString(2, producto.getNomProducto());
                pst.setInt(3, idCategoria); // Utilizar el identificador de categoría obtenido
                pst.setString(4, producto.getTam());
                pst.setFloat(5, producto.getPrecio());
                pst.setInt(6, producto.getStock());
                pst.execute();
                pst.close();
                mensaje = "GUARDADO CORRECTAMENTE";
            } else {
                mensaje = "ERROR: No se encontró la categoría especificada";
            }
        } catch (SQLException e) {
            mensaje = "ERROR: " + e.getMessage();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mensaje;
    }

    public String actualizarProducto(Connection con, Producto producto, String nomCategoria) {
        String mensaje;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sqlCategoria = "SELECT idCategoria FROM CATEGORIA WHERE nomCategoria = ?";
        String sql = "UPDATE PRODUCTO SET nomProducto=?, idCategoria=?, tam=?, precio=?, stock=? WHERE idProducto=?";
        try {
            pst = con.prepareStatement(sqlCategoria);
            pst.setString(1, nomCategoria);
            rs = pst.executeQuery();
            int idCategoria = -1;
            if (rs.next()) {
                idCategoria = rs.getInt("idCategoria");
            }
            rs.close();
            pst.close();

            if (idCategoria != -1) {
                pst = con.prepareStatement(sql);
                pst.setString(1, producto.getNomProducto());
                pst.setInt(2, idCategoria);
                pst.setString(3, producto.getTam());
                pst.setFloat(4, producto.getPrecio());
                pst.setInt(5, producto.getStock());
                pst.setInt(6, producto.getIdProducto());
                pst.execute();
                pst.close();
                mensaje = "ACTUALIZADO CORRECTAMENTE";
            } else {
                mensaje = "ERROR: No se encontró la categoría especificada";
            }
        } catch (SQLException e) {
            mensaje = "ERROR: " + e.getMessage();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mensaje;
    }

    public String eliminarProducto(Connection con, int idProducto) {
        String mensaje;
        PreparedStatement pst = null;
        String sql = "DELETE FROM PRODUCTO WHERE idProducto=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idProducto);
            pst.execute();
            pst.close();
            mensaje = "ELIMINADO CORRECTAMENTE";
        } catch (SQLException e) {
            mensaje = "ERROR: " + e.getMessage();
        }
        return mensaje;
    }

    public String eliminarTodosLosProductos(Connection con) {
        String mensaje;
        PreparedStatement pst = null;
        String sql = "DELETE FROM PRODUCTO";
        try {
            pst = con.prepareStatement(sql);
            pst.execute();
            pst.close();
            mensaje = "ELIMINADOS TODOS";
        } catch (SQLException e) {
            mensaje = "ERROR: " + e.getMessage();
        }
        return mensaje;
    }

    public String getMaxID(Connection con) {
        String id = "1"; // Establecer el valor predeterminado como "1"
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT MAX(idProducto+1) FROM PRODUCTO";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                String maxID = rs.getString(1);
                if (maxID != null) {
                    id = maxID; // Actualizar el valor solo si no es nulo
                }
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            // Manejar la excepción
        }
        return id;
    }

    public void listarProducto(Connection con, JTable tabla) {
        DefaultTableModel model;
        String[] columnas = {"ID", "NOMBRE PRODUCTO", "CATEGORÍA", "TAMAÑO", "PRECIO", "STOCK"};
        model = new DefaultTableModel(null, columnas);
        String sql = "SELECT p.idProducto, p.nomProducto, c.nomCategoria, p.tam, p.precio, p.stock "
                + "FROM PRODUCTO p INNER JOIN CATEGORIA c ON p.idCategoria = c.idCategoria "
                + "ORDER BY p.idProducto ASC";
        String[] filas = new String[6];
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                filas[0] = rs.getString(1);
                filas[1] = rs.getString(2);
                filas[2] = rs.getString(3);
                filas[3] = rs.getString(4);
                filas[4] = rs.getString(5);
                filas[5] = rs.getString(6);
                model.addRow(filas);
            }
            tabla.setModel(model);
        } catch (SQLException e) {
            System.out.println("Error al listar la tabla: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void listarProductoBusqueda(Connection con, JTable tabla, String valorBusqueda) {
        DefaultTableModel model;
        String[] columnas = {"ID", "NOMBRE PRODUCTO", "CATEGORÍA", "TAMAÑO", "PRECIO", "STOCK"};
        model = new DefaultTableModel(null, columnas);
        String sql = "SELECT p.idProducto, p.nomProducto, c.nomCategoria, p.tam, p.precio, p.stock "
                + "FROM PRODUCTO p INNER JOIN CATEGORIA c ON p.idCategoria = c.idCategoria "
                + "WHERE p.idProducto = ? OR p.nomProducto LIKE ? OR c.nomCategoria LIKE ? OR p.tam LIKE ? "
                + "OR p.precio = ? OR p.stock = ? "
                + "ORDER BY p.idProducto ASC";
        String[] filas = new String[6];
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, valorBusqueda);
            for (int i = 2; i <= 6; i++) {
                pst.setString(i, "%" + valorBusqueda + "%");
            }
            rs = pst.executeQuery();
            while (rs.next()) {
                filas[0] = rs.getString(1);
                filas[1] = rs.getString(2);
                filas[2] = rs.getString(3);
                filas[3] = rs.getString(4);
                filas[4] = rs.getString(5);
                filas[5] = rs.getString(6);
                model.addRow(filas);
            }
            tabla.setModel(model);
        } catch (SQLException e) {
            System.out.println("Error al listar la tabla: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Producto> findProducto(Connection con) {
        List<Producto> productos = new ArrayList<>();
        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            String sql = "SELECT idProducto, nomProducto, tam, precio, stock FROM PRODUCTO ORDER BY nomProducto";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Producto pro = new Producto();
                pro.setIdProducto(rs.getInt("idProducto"));
                pro.setNomProducto(rs.getString("nomProducto"));
                pro.setTam(rs.getString("tam"));
                pro.setPrecio(rs.getFloat("precio"));
                pro.setStock(rs.getInt("stock"));
                productos.add(pro);
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println("Error al mostrar productos: " + e.getMessage());
        }
        return productos;
    }

}
