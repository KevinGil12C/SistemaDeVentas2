package dao;

/**
 *
 * @author Kevscl
 */
import java.sql.*;

public class RecuperaDatosReportesDAO {

    public int contarTiposDeProductos(Connection con) {
        String sql = "SELECT COUNT(DISTINCT idCategoria) AS totalTipos FROM PRODUCTO";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int totalTipos = rs.getInt("totalTipos");
            rs.close();
            stmt.close();
            return totalTipos;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int contarVentas(Connection con) {
        String sql = "SELECT COUNT(*) AS totalVentas FROM VENTA";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int totalVentas = rs.getInt("totalVentas");
            rs.close();
            stmt.close();
            return totalVentas;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public float obtenerTotalVentas(Connection con) {
        String sql = "SELECT SUM(total) AS totalVentas FROM VENTA";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            float totalVentas = rs.getFloat("totalVentas");
            rs.close();
            stmt.close();
            return totalVentas;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float obtenerTotalCompras(Connection con) {
        String sql = "SELECT SUM(total) AS totalCompras FROM COMPRAS";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            float totalCompras = rs.getFloat("totalCompras");
            rs.close();
            stmt.close();
            return totalCompras;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0f;
        }

    }

    public int contarPagosPendientes(Connection con) {
        String sql = "SELECT COUNT(*) AS totalPagosPendientes FROM VistaVentaPagos";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int totalPagosPendientes = rs.getInt("totalPagosPendientes");
            rs.close();
            stmt.close();
            return totalPagosPendientes;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
