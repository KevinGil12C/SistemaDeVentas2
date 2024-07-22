package ajustes;

/**
 *
 * @author Kevscl
 */
import static bd.Conexion.getConnection;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Reset {

    public void eliminarRegistros() {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();

            // Eliminar registros de tablas sin dependencias
            stmt.executeUpdate("DELETE FROM COMPRAS");
            stmt.executeUpdate("DELETE FROM PAGO_VENTA");

            // Eliminar registros de tablas con dependencias
            stmt.executeUpdate("DELETE FROM DETALLE_VENTA");
            stmt.executeUpdate("DELETE FROM DEVOLUCION");

            // Eliminar registros de tablas principales
            stmt.executeUpdate("DELETE FROM VENTA");
            stmt.executeUpdate("DELETE FROM CLIENTE");
            stmt.executeUpdate("DELETE FROM EMPLEADO");
            stmt.executeUpdate("DELETE FROM PRODUCTO");
            stmt.executeUpdate("DELETE FROM CATEGORIA");

            // Insertar nuevo cliente
            String insertQuery = "INSERT INTO CLIENTE (idCliente, nomCliente, ape1Cliente, ape2Cliente, rfcCliente, generoCliente, correoCliente, telCliente) "
                    + "VALUES (1, 'VENTAS', 'PÚBLICO', 'EN GENERAL', 'XAXX010101000', 'Hombre', 'kebo.jcg77@gmail.com', '7221595250')";
            stmt.executeUpdate(insertQuery);

            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar registros e insertar nuevo cliente: " + e.getMessage());
        }
    }

    public void eliminarVentas() {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();

            // Eliminar registros de tablas sin dependencias
            //stmt.executeUpdate("DELETE FROM COMPRAS");
            stmt.executeUpdate("DELETE FROM PAGO_VENTA");

            // Eliminar registros de tablas con dependencias
            stmt.executeUpdate("DELETE FROM DETALLE_VENTA");
            stmt.executeUpdate("DELETE FROM DEVOLUCION");

            // Eliminar registros de tablas principales
            stmt.executeUpdate("DELETE FROM VENTA");
            //stmt.executeUpdate("DELETE FROM CLIENTE");
            //stmt.executeUpdate("DELETE FROM EMPLEADO");
            //stmt.executeUpdate("DELETE FROM PRODUCTO");
            //stmt.executeUpdate("DELETE FROM CATEGORIA");

            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar registros: " + e.getMessage());
        }
    }
}
