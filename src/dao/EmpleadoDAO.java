package dao;

/**
 *
 * @author Kevscl
 */
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import entity.*;

public class EmpleadoDAO {

    private static String mensaje = "";
    private String sql = "";

    public String agregarEmpleado(Connection con, Empleado empleado) {
        PreparedStatement pst = null;
        String sql = "INSERT INTO Empleado(idEmpleado, nombre, ape1Usuario, ape2Usuario, usuario, clave, correo) VALUES(?,?,?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, empleado.getIdEmpleado());
            pst.setString(2, empleado.getNombre());
            pst.setString(3, empleado.getApe1Usuario());
            pst.setString(4, empleado.getApe2Usuario());
            pst.setString(5, empleado.getUsuario());
            pst.setString(6, empleado.getClave());
            pst.setString(7, empleado.getCorreo());
            pst.execute();
            pst.close();
            return "GUARDADO CORRECTAMENTE";
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }

    public String actualizarEmpleado(Connection con, Empleado empleado) {
        PreparedStatement pst = null;
        String sql = "UPDATE Empleado SET nombre=?, ape1Usuario=?, ape2Usuario=?, usuario=?, clave=?, correo=? WHERE idEmpleado=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, empleado.getNombre());
            pst.setString(2, empleado.getApe1Usuario());
            pst.setString(3, empleado.getApe2Usuario());
            pst.setString(4, empleado.getUsuario());
            pst.setString(5, empleado.getClave());
            pst.setString(6, empleado.getCorreo());
            pst.setInt(7, empleado.getIdEmpleado());
            pst.execute();
            pst.close();
            return "ACTUALIZADO CORRECTAMENTE";
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }

    public String eliminarEmpleado(Connection con, int idEmpleado) {
        PreparedStatement pst = null;
        String sql = "DELETE FROM Empleado WHERE idEmpleado=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idEmpleado);
            pst.execute();
            pst.close();
            return "ELIMINADO CORRECTAMENTE";
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }

    public String eliminarTodosEmpleados(Connection con) {
        PreparedStatement pst = null;
        String sql = "DELETE FROM EMPLEADO";
        try {
            pst = con.prepareStatement(sql);
            pst.execute();
            pst.close();
            return "ELIMINADOS TODOS";
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }

    public String getMaxID(Connection con) {
        String id = "1"; // Establecer el valor predeterminado como "1"
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT MAX(idEmpleado+1) FROM EMPLEADO";
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

    public String validarUsuario(Connection con, String usuario, String clave) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Empleado WHERE usuario = ? AND clave = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, usuario);
            pst.setString(2, clave);
            rs = pst.executeQuery();

            if (rs.next()) {
                // El usuario y contraseña son válidos
                pst.close();
                rs.close();
                return "Usuario y contraseña válidos";
            } else {
                // El usuario y/o contraseña son incorrectos
                pst.close();
                rs.close();
                return "Usuario y/o contraseña incorrectos";
            }
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }

    public String recuperarContraseña(Connection con, String nombre, String ape1Usuario, String ape2Usuario, String correo, String usuario) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        String contraseña = null;
        String sql = "SELECT clave FROM EMPLEADO WHERE nombre = ? AND ape1Usuario = ? AND ape2Usuario = ? AND correo = ? AND usuario=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nombre);
            pst.setString(2, ape1Usuario);
            pst.setString(3, ape2Usuario);
            pst.setString(4, correo);
            pst.setString(5, usuario);
            rs = pst.executeQuery();
            if (rs.next()) {
                contraseña = rs.getString("clave");
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            return "ERROR";
        }
        return contraseña;
    }

    public void listarEmpleado(Connection con, JTable tabla) {
        DefaultTableModel model;
        String[] columnas = {"ID", "NOMBRE", "APELLIDO PATERNO", "APELLIDO MATERNO", "USUARIO", "CLAVE", "CORREO"};
        model = new DefaultTableModel(null, columnas);
        String sql = "SELECT * FROM EMPLEADO ORDER BY idEmpleado ASC";
        String[] filas = new String[7]; // Se cambia a 7 para incluir el campo correo
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                for (int i = 0; i < 7; i++) { // Se cambia a 7 para incluir el campo correo
                    filas[i] = rs.getString(i + 1);
                }
                model.addRow(filas);
            }
            tabla.setModel(model);
        } catch (Exception e) {
            System.out.println("NO SE PUEDE LISTAR LA TABLA");
        }
    }

}
