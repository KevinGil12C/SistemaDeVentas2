package bo;

import bd.Conexion;
import dao.*;
import entity.*;
import java.sql.*;
import javax.swing.JTable;

/**
 *
 * @author Kevscl
 */
public class EmpleadoBO {

    private static String mensaje = "";
    private EmpleadoDAO emp = new EmpleadoDAO();

    public String agregarEmpleado(Empleado user) {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = emp.agregarEmpleado(conn, user);
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

    public String actualizarEmpleado(Empleado user) {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = emp.actualizarEmpleado(conn, user);
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

    public String eliminarEmpleado(int id) {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = emp.eliminarEmpleado(conn, id);
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

    public String eliminarTodosEmpleados() {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = emp.eliminarTodosEmpleados(conn);
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
        String id = emp.getMaxID(conn);
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public String validarUsuario(String usuario, String clave) {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = emp.validarUsuario(conn, usuario, clave);
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
    
    public String recuperarContraseña(String nombre, String ape1Usuario, String ape2Usuario, String correo, String usuario) {
        Connection conn = Conexion.getConnection();
        try {
            mensaje = emp.recuperarContraseña(conn, nombre, ape1Usuario, ape2Usuario, correo, usuario);
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
    public void listarEmpleado(JTable tabla) {
        Connection conn = Conexion.getConnection();
        emp.listarEmpleado(conn, tabla);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
