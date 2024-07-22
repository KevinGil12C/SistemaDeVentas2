package dao;

/**
 *
 * @author Kevscl
 */
import java.sql.*;
import entity.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DevolucionDAO {

    public String agregarDevolucion(Connection con, Devolucion d, String producto) {
        String mensaje;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "INSERT INTO DEVOLUCION (idDevolucion, idVenta, idProducto, cantidadDevuelta, fechaDevuelta, motivo) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            // Obtener el identificador de empleado basado en el nombre de categoría
            String sqlProducto = "SELECT idProducto FROM PRODUCTO WHERE nomProducto || ' ' || tam = ?";
            pst = con.prepareStatement(sqlProducto);
            pst.setString(1, producto);
            rs = pst.executeQuery();
            int idProducto = -1; // Valor predeterminado para el identificador de categoría
            if (rs.next()) {
                idProducto = rs.getInt("idProducto");
            }
            rs.close();
            pst.close();

            if (idProducto != -1) {
                pst = con.prepareStatement(sql);
                pst.setInt(1, d.getIdDevolucion());
                pst.setInt(2, d.getIdVenta());
                pst.setInt(3, idProducto);
                pst.setInt(4, d.getCantidadDevuelta());
                pst.setString(5, d.getFechaDevuelta());
                pst.setString(6, d.getMotivo());

                pst.execute();
                pst.close();
                mensaje = "DEVOLUCIÓN REGISTRADA CORRECTAMENTE";
            } else {
                mensaje = "ERROR";
            }
        } catch (SQLException e) {
            mensaje = "ERROR: " + e.getMessage();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mensaje;
    }

    public String getMaxID(Connection con) {
        String id = "1"; // Establecer el valor predeterminado como "1"
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT MAX(idDevolucion+1) FROM DEVOLUCION";
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

    public String listarDevolucion(Connection con, JTable tabla) {
        DefaultTableModel model;
        String[] columnas = {"ID DEVOLUCIÓN", "ID VENTA", "PRODUCTO", "PZ DEVO", "FECHA", "MOTIVO"};
        model = new DefaultTableModel(null, columnas);
        String sql = "SELECT * FROM VistaDevoluciones";
        String[] filas = new String[6];
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean hayDevoluciones = false;
        String mensaje;

        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                hayDevoluciones = true;
                for (int i = 0; i < 6; i++) {
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

        if (hayDevoluciones) {
            mensaje = "HAY DEVOLUCIONES";
        } else {
            mensaje = "NO HAY DEVOLUCIONES";
        }

        return mensaje;
    }

    public String listarDevolucionFecha(Connection con, JTable tabla, String fecha) {
        DefaultTableModel model;
        String[] columnas = {"ID DEVOLUCIÓN", "ID VENTA", "PRODUCTO", "PZ DEVO", "FECHA", "MOTIVO"};
        model = new DefaultTableModel(null, columnas);
        String sql = "SELECT * FROM VistaDevoluciones WHERE fechaDevuelta = ?";
        String[] filas = new String[6];
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean hayDevoluciones = false;
        String mensaje;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, fecha);
            rs = pst.executeQuery();
            while (rs.next()) {
                hayDevoluciones = true;
                for (int i = 0; i < 6; i++) {
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

        if (hayDevoluciones) {
            mensaje = "HAY DEVOLUCIONES";
        } else {
            mensaje = "NO HAY DEVOLUCIONES";
        }

        return mensaje;
    }

    public String listarDevolucionBusqueda(Connection con, JTable tabla, String textoBusqueda) {
        DefaultTableModel model;
        String[] columnas = {"ID DEVOLUCIÓN", "ID VENTA", "PRODUCTO", "PZ DEVO", "FECHA", "MOTIVO"};
        model = new DefaultTableModel(null, columnas);
        String sql = "SELECT * FROM VistaDevoluciones WHERE "
                + "idDevolucion LIKE ? OR "
                + "idVenta LIKE ? OR "
                + "productoTam LIKE ? OR "
                + "cantidadDevuelta LIKE ? OR "
                + "fechaDevuelta LIKE ? OR "
                + "motivo LIKE ?";
        String[] filas = new String[6];
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean hayDevoluciones = false;
        String mensaje;

        try {
            pst = con.prepareStatement(sql);
            for (int i = 1; i <= 6; i++) {
                pst.setString(i, "%" + textoBusqueda + "%");
            }
            rs = pst.executeQuery();
            while (rs.next()) {
                hayDevoluciones = true;
                for (int i = 0; i < 6; i++) {
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

        if (hayDevoluciones) {
            mensaje = "HAY DEVOLUCIONES";
        } else {
            mensaje = "NO HAY DEVOLUCIONES";
        }

        return mensaje;
    }

}
