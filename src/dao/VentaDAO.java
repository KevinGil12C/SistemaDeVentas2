package dao;

/**
 *
 * @author Kevscl
 */
import java.sql.*;
import entity.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VentaDAO {

    public String agregarVenta(Connection con, Venta v, String nomCliente, String user) {
        String mensaje;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "INSERT INTO VENTA (idVenta, idEmpleado, idCliente, fecha, mPago, total) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            // Obtener el identificador de empleado basado en el nombre de categoría
            String sqlEmpleado = "SELECT idEmpleado FROM EMPLEADO WHERE usuario = ?";
            pst = con.prepareStatement(sqlEmpleado);
            pst.setString(1, user);
            rs = pst.executeQuery();
            int idEmpleado = -1; // Valor predeterminado para el identificador de categoría
            if (rs.next()) {
                idEmpleado = rs.getInt("idEmpleado");
            }
            rs.close();
            pst.close();

            // Obtener el identificador de cliente basado en el nombre de categoría
            String sqlCliente = "SELECT idCliente FROM CLIENTE WHERE nomCliente || ' ' || ape1Cliente || ' ' || ape2Cliente = ?";
            pst = con.prepareStatement(sqlCliente);
            pst.setString(1, nomCliente);
            rs = pst.executeQuery();
            int idCliente = -1; // Valor predeterminado para el identificador de categoría
            if (rs.next()) {
                idCliente = rs.getInt("idCliente");
            }
            rs.close();
            pst.close();

            if (idEmpleado != -1 && idCliente != -1) {
                pst = con.prepareStatement(sql);
                pst.setInt(1, v.getIdVenta());
                pst.setInt(2, idEmpleado);
                pst.setInt(3, idCliente);
                pst.setString(4, v.getFecha());
                pst.setString(5, v.getmPago());
                pst.setFloat(6, v.getTotal());

                pst.execute();
                pst.close();
                mensaje = "GUARDADO CORRECTAMENTE";
            } else {
                mensaje = "ERROR";
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

    public String agregarDetalleVenta(Connection con, DetalleVenta dv, String producto) {
        String mensaje;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "INSERT INTO Detalle_Venta(idDetalle, idProducto, idVenta, cantidad, pUnitario, total) VALUES(?, ?, ?, ?, ?, ?)";
        //System.out.println(producto);
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
                pst.setInt(1, dv.getIdDetalleVenta());
                pst.setInt(2, idProducto);
                pst.setInt(3, dv.getIdVenta());
                pst.setInt(4, dv.getCantidad());
                pst.setFloat(5, dv.getpUnitario());
                pst.setFloat(6, dv.getTotal());
                pst.execute();
                pst.close();
                //Si se ejecuta correctamente regresa el siguiente mensaje
                mensaje = "GUARDADO CORRECTAMENTE";
            } else {
                mensaje = "ERROR";
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

    public String getMaxID(Connection con) {
        String id = "1"; // Establecer el valor predeterminado como "1"
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT MAX(idVenta+1) FROM VENTA";
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

    public void listarVentas(Connection con, JTable tabla) {
        DefaultTableModel model;
        String[] columnas = {"CONT", "ID VENTA", "CLIENTE", "FECHA", "PRODUCTO", "CANTIDAD", "P/UNITARIO", "TOTAL"};
        model = new DefaultTableModel(null, columnas);
        String sql = "SELECT * FROM VistaDetalleVenta";
        String[] filas = new String[8];
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                for (int i = 0; i < 8; i++) {
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

    public String listarVentasPorFecha(Connection con, JTable tabla, String fecha) {
        DefaultTableModel model;
        String[] columnas = {"CONT", "ID VENTA", "CLIENTE", "FECHA", "PRODUCTO", "CANTIDAD", "P/UNITARIO", "TOTAL"};
        model = new DefaultTableModel(null, columnas);

        String sql = "SELECT * FROM VistaDetalleVenta WHERE FECHA = ?";
        String[] filas = new String[8];
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean ventasEncontradas = false;
        String mensaje;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, fecha);
            rs = pst.executeQuery();

            while (rs.next()) {
                ventasEncontradas = true;
                for (int i = 0; i < 8; i++) {
                    filas[i] = rs.getString(i + 1);
                }
                model.addRow(filas);
            }
            tabla.setModel(model);

            if (ventasEncontradas) {
                mensaje = "VENTAS ENCONTRADAS";
            } else {
                mensaje = "NO HAY VENTAS";
            }
        } catch (SQLException e) {
            mensaje = "NO SE PUEDE LISTAR LA TABLA";
            e.printStackTrace();
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

        return mensaje;
    }

    public String listarVentasReporte(Connection con, JTable tabla, String fecha) {
        DefaultTableModel model;
        String[] columnas = {"ID VENTA", "VENDEDOR", "CLIENTE", "FECHA", "MÉTODO DE PAGO", "TOTAL"};
        model = new DefaultTableModel(null, columnas);

        String sql = "SELECT * FROM VistaVenta WHERE FECHA = ?";
        String[] filas = new String[6];
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean ventasEncontradas = false;
        String mensaje;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, fecha);
            rs = pst.executeQuery();

            while (rs.next()) {
                ventasEncontradas = true;
                for (int i = 0; i < 6; i++) {
                    filas[i] = rs.getString(i + 1);
                }
                model.addRow(filas);
            }
            tabla.setModel(model);

            if (ventasEncontradas) {
                mensaje = "VENTAS ENCONTRADAS";
            } else {
                mensaje = "NO HAY VENTAS";
            }
        } catch (SQLException e) {
            mensaje = "NO SE PUEDE LISTAR LA TABLA";
            e.printStackTrace();
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

        return mensaje;
    }

    public String listarVentasReporteTotal(Connection con, JTable tabla) {
        DefaultTableModel model;
        String[] columnas = {"ID VENTA", "VENDEDOR", "CLIENTE", "FECHA", "MÉTODO DE PAGO", "TOTAL"};
        model = new DefaultTableModel(null, columnas);

        String sql = "SELECT * FROM VistaVenta";
        String[] filas = new String[6];
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean ventasEncontradas = false;
        String mensaje;

        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                ventasEncontradas = true;
                for (int i = 0; i < 6; i++) {
                    filas[i] = rs.getString(i + 1);
                }
                model.addRow(filas);
            }
            tabla.setModel(model);

            if (ventasEncontradas) {
                mensaje = "VENTAS ENCONTRADAS";
            } else {
                mensaje = "NO HAY VENTAS";
            }
        } catch (SQLException e) {
            mensaje = "NO SE PUEDE LISTAR LA TABLA";
            e.printStackTrace();
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

        return mensaje;
    }

    public String listarVentasReporteTotalBusqueda(Connection con, JTable tabla, String textoBusqueda) {
        DefaultTableModel model;
        String[] columnas = {"ID VENTA", "VENDEDOR", "CLIENTE", "FECHA", "MÉTODO DE PAGO", "TOTAL"};
        model = new DefaultTableModel(null, columnas);

        String sql = "SELECT * FROM VistaVenta WHERE "
                + "idVenta LIKE ? OR "
                + "usuarioEmpleado LIKE ? OR "
                + "nombreCompletoCliente LIKE ? OR "
                + "fecha LIKE ? OR "
                + "mPago LIKE ? OR "
                + "total LIKE ?";
        String[] filas = new String[6];
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean ventasEncontradas = false;
        String mensaje;

        try {
            pst = con.prepareStatement(sql);
            for (int i = 1; i <= 6; i++) {
                pst.setString(i, "%" + textoBusqueda + "%");
            }
            rs = pst.executeQuery();

            while (rs.next()) {
                ventasEncontradas = true;
                for (int i = 0; i < 6; i++) {
                    filas[i] = rs.getString(i + 1);
                }
                model.addRow(filas);
            }
            tabla.setModel(model);

            if (ventasEncontradas) {
                mensaje = "VENTAS ENCONTRADAS";
            } else {
                mensaje = "NO HAY VENTAS";
            }
        } catch (SQLException e) {
            mensaje = "NO SE PUEDE LISTAR LA TABLA";
            e.printStackTrace();
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

        return mensaje;
    }

}
