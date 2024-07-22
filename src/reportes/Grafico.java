package reportes;

/**
 *
 * @author Kevscl
 */
import bd.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Grafico {

    public ChartPanel ProductosMasVendidos() {
        ChartPanel chartPanel = null;
        Connection con = Conexion.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT p.nomProducto AS nombre, p.tam, SUM(dv.cantidad) AS cantidad_total\n"
                    + "FROM PRODUCTO p\n"
                    + "INNER JOIN DETALLE_VENTA dv ON p.idProducto = dv.idProducto\n"
                    + "GROUP BY p.nomProducto, p.tam\n"
                    + "ORDER BY cantidad_total DESC"; // Ordenar por cantidad total vendida en orden descendente

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            DefaultPieDataset dataset = new DefaultPieDataset();

            while (rs.next()) {
                String nombreProducto = rs.getString("nombre");
                String tamProducto = rs.getString("tam");
                int cantidadTotal = rs.getInt("cantidad_total");

                String label = nombreProducto + " (" + tamProducto + ")"; // Etiqueta del producto con su tamaño
                dataset.setValue(label, cantidadTotal);
            }

            JFreeChart chart = ChartFactory.createPieChart("Productos más vendidos", dataset);
            PiePlot plot = (PiePlot) chart.getPlot();
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

            chartPanel = new ChartPanel(chart);

        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }

        return chartPanel;
    }

    public static void ProductosMasVendidos(JPanel panel) {
        System.out.println("Cargando gráfico de productos más vendidos...");
        try {
            Connection con = Conexion.getConnection();
            String sql = "SELECT p.nomProducto AS nombre, p.tam, SUM(dv.cantidad) AS cantidad_total\n"
                    + "FROM PRODUCTO p\n"
                    + "INNER JOIN DETALLE_VENTA dv ON p.idProducto = dv.idProducto\n"
                    + "GROUP BY p.nomProducto, p.tam\n"
                    + "ORDER BY cantidad_total DESC";

            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            DefaultPieDataset dataset = new DefaultPieDataset();

            while (rs.next()) {
                String nombreProducto = rs.getString("nombre");
                String tamProducto = rs.getString("tam");
                int cantidadTotal = rs.getInt("cantidad_total");

                String label = nombreProducto + " (" + tamProducto + ")";
                dataset.setValue(label, cantidadTotal);
            }

            JFreeChart chart = ChartFactory.createPieChart("Productos más vendidos", dataset);
            PiePlot plot = (PiePlot) chart.getPlot();
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

            ChartPanel chartPanel = new ChartPanel(chart);
            panel.removeAll();
            panel.add(chartPanel);
            panel.revalidate();
            panel.repaint();

            rs.close();
            pst.close();
            con.close();

            System.out.println("Gráfico de productos más vendidos cargado exitosamente.");

        } catch (Exception e) {
            System.out.println("Error al mostrar el gráfico: " + e.toString());
        }
    }

    public ChartPanel ProductosEnStockMinimo() {
        ChartPanel chartPanel = null;
        Connection con = Conexion.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT p.nomProducto||' '||p.tam AS nombre, p.stock\n"
                    + "FROM PRODUCTO p\n"
                    + "WHERE p.stock <= 10\n"
                    + "ORDER BY p.stock ASC"; // Ordenar por stock en orden ascendente

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            while (rs.next()) {
                String nombreProducto = rs.getString("nombre");
                int stock = rs.getInt("stock");

                dataset.addValue(stock, "Stock", nombreProducto);
            }

            JFreeChart chart = ChartFactory.createBarChart("Productos en Stock Mínimo", "Producto", "Stock", dataset);
            CategoryPlot plot = chart.getCategoryPlot();
            BarRenderer renderer = (BarRenderer) plot.getRenderer();

            // Ajustar el ancho de las barras (valor entre 0.0 y 1.0)
            renderer.setMaximumBarWidth(0.1); // Puedes ajustar este valor según tus necesidades

            chartPanel = new ChartPanel(chart); // Asignamos el gráfico al ChartPanel

        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }

        return chartPanel;
    }
}
