package dao;

import entity.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kevscl
 */
public class ClienteDAO {

    private static String mensaje = "";
    private String sql = "";

    public String agregarCliente(Connection con, Cliente c) {
        PreparedStatement pst = null;
        String sql = "INSERT INTO CLIENTE(idCliente, nomCliente, ape1Cliente, ape2Cliente, rfcCliente, generoCliente, correoCliente, telCliente) VALUES(?,?,?,?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, c.getIdCliente());
            pst.setString(2, c.getNomCliente());
            pst.setString(3, c.getApe1Cliente());
            pst.setString(4, c.getApe2Cliente());
            pst.setString(5, c.getRfcCliente());
            pst.setString(6, c.getGeneroCliente());
            pst.setString(7, c.getCorreoCliente());
            pst.setString(8, c.getTelCliente());
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
        String sql = "SELECT MAX(idcliente) FROM CLIENTE";
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

    public String actualizarCliente(Connection con, Cliente c) {
        PreparedStatement pst = null;
        String sql = "UPDATE CLIENTE SET nomCliente=?, ape1Cliente=?, ape2Cliente=?, rfcCliente=?, generoCliente=?, correoCliente=?, telCliente=? WHERE idCliente=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, c.getNomCliente());
            pst.setString(2, c.getApe1Cliente());
            pst.setString(3, c.getApe2Cliente());
            pst.setString(4, c.getRfcCliente());
            pst.setString(5, c.getGeneroCliente());
            pst.setString(6, c.getCorreoCliente());
            pst.setString(7, c.getTelCliente());
            pst.setInt(8, c.getIdCliente());
            pst.execute();
            pst.close();
            return "ACTUALIZADO CORRECTAMENTE";
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }

    public String eliminarCliente(Connection con, int id) {
        PreparedStatement pst = null;
        String sql = "DELETE FROM CLIENTE WHERE idCliente=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            pst.close();
            return "ELIMINADO CORRECTAMENTE";
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }

    public void listarCliente(Connection con, JTable tabla) {
        DefaultTableModel model;
        String[] columnas = {"ID", "NOMBRE CLIENTE", "APELLIDO PATERNO", "APELLIDO MATERNO", "RFC", "GÉNERO", "CORREO", "TELÉFONO"};
        model = new DefaultTableModel(null, columnas);
        String sql = "SELECT * FROM CLIENTE ORDER BY idCliente ASC";
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
        }
    }

    public void listarClienteBusqueda(Connection con, JTable tabla, String valorBusqueda) {
        DefaultTableModel model;
        String[] columnas = {"ID", "NOMBRE CLIENTE", "APELLIDO PATERNO", "APELLIDO MATERNO", "RFC", "GÉNERO", "CORREO", "TELÉFONO"};
        model = new DefaultTableModel(null, columnas);
        String sql = "SELECT * FROM CLIENTE WHERE idCliente = ? OR nomCliente LIKE ? OR ape1Cliente LIKE ? OR ape2Cliente LIKE ? OR rfcCliente LIKE ? OR generoCliente LIKE ? OR correoCliente LIKE ? OR telCliente LIKE ? ORDER BY idCliente ASC";
        String[] filas = new String[8];
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, valorBusqueda);
            for (int i = 2; i <= 8; i++) {
                pst.setString(i, "%" + valorBusqueda + "%");
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

    public String eliminarTodosClientes(Connection con) {
        PreparedStatement pst = null;
        String sql = "DELETE FROM CLIENTE";
        try {
            pst = con.prepareStatement(sql);
            pst.execute();
            pst.close();
            return "ELIMINADOS TODOS";
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }

    public List<Cliente> obtenerClientes(Connection con) {
        List<Cliente> clientes = new ArrayList<>();
        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            String sql = "SELECT idCliente, nomCliente, ape1Cliente, ape2Cliente, rfcCliente, generoCliente, correoCliente, telCliente FROM CLIENTE ORDER BY nomCliente";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNomCliente(rs.getString("nomCliente"));
                cliente.setApe1Cliente(rs.getString("ape1Cliente"));
                cliente.setApe2Cliente(rs.getString("ape2Cliente"));
                cliente.setRfcCliente(rs.getString("rfcCliente"));
                cliente.setGeneroCliente(rs.getString("generoCliente"));
                cliente.setCorreoCliente(rs.getString("correoCliente"));
                cliente.setTelCliente(rs.getString("telCliente"));
                clientes.add(cliente);
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener clientes: " + e.getMessage());
        }
        return clientes;
    }

    public String obtenerCorreoClientePorVenta(Connection con, int idVenta) {
        String correoCliente = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT c.correoCliente FROM VENTA v INNER JOIN CLIENTE c ON v.idCliente = c.idCliente WHERE v.idVenta = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idVenta);
            rs = pst.executeQuery();

            if (rs.next()) {
                correoCliente = rs.getString("correoCliente");
            }

            rs.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return correoCliente;
    }

}
