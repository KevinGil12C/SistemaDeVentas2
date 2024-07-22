package dao;

/**
 *
 * @author Kevscl
 */
import entity.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PagoVentaDAO {

    public String agregarPagoVenta(Connection con, PagoVentas pv) {
        String mensaje;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "INSERT INTO PAGO_VENTA(idPago, idVenta, fechaPago, monto, nombreClienteNoRegistrado) VALUES(?, ?, ?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, pv.getIdPago());
            pst.setInt(2, pv.getIdVenta());
            pst.setString(3, pv.getFechaPago());
            pst.setFloat(4, pv.getAbono());
            pst.setString(5, pv.getNombreCliente()); // Usar el nombre del cliente no registrado
            pst.execute();
            pst.close();
            mensaje = "GUARDADO CORRECTAMENTE";
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

    public String abonarPagoVenta(Connection con, PagoVentas pv) {
        String mensaje;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "UPDATE PAGO_VENTA SET monto = monto+? WHERE nombreClienteNoRegistrado=? AND idVenta=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setFloat(1, pv.getAbono());
            pst.setString(2, pv.getNombreCliente()); // Usar el nombre del cliente no registrado
            pst.setInt(3, pv.getIdVenta());
            pst.execute();
            pst.close();
            mensaje = "GUARDADO CORRECTAMENTE";
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
        String sql = "SELECT MAX(idPago+1) FROM PAGO_VENTA";
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

    public void listarVentaPagos(Connection con, JTable tabla) {
        DefaultTableModel model;
        String[] columnas = {"ID VENTA", "VENDEDOR", "CLIENTE", "CLIENTE NO REGISTRADO", "FECHA", "M. PAGO", "TOTAL", "PAGADO"};
        model = new DefaultTableModel(null, columnas);
        String sql = "SELECT * FROM VistaVentaPagos";
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

    public void listarVentaPagosBusqueda(Connection con, JTable tabla, String textoBusqueda) {
        DefaultTableModel model;
        String[] columnas = {"ID VENTA", "VENDEDOR", "CLIENTE", "CLIENTE NO REGISTRADO", "FECHA", "M. PAGO", "TOTAL", "PAGADO"};
        model = new DefaultTableModel(null, columnas);
        String sql = "SELECT * FROM VistaVentaPagos WHERE "
                + "idVenta LIKE ? OR "
                + "usuarioEmpleado LIKE ? OR "
                + "nombreCliente LIKE ? OR "
                + "nombreClienteNoRegistrado LIKE ? OR "
                + "fecha LIKE ? OR "
                + "mPago LIKE ? OR "
                + "total LIKE ? OR "
                + "totalPagado LIKE ?";
        String[] filas = new String[8];
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(sql);
            for (int i = 1; i <= 8; i++) {
                pst.setString(i, "%" + textoBusqueda + "%");
            }
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

    public void listarPagos(Connection con, JTable tabla) {
        DefaultTableModel model;
        String[] columnas = {"ID PAGO", "ID VENTA", "FECHA", "ABONO", "CLIENTE"};
        model = new DefaultTableModel(null, columnas);
        String sql = "SELECT * FROM PAGO_VENTA";
        String[] filas = new String[5];
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                for (int i = 0; i < 5; i++) {
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
}
