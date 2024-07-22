package reportes;

import bd.Conexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class MiFrame extends JFrame {

    private JPanel panelGrafico1;
    private JPanel panelGrafico2;

    public MiFrame() {
        initComponents();
    }

    private void initComponents() {
        // Configura el JFrame y otros componentes aquí

        // Crea un menú y un item para mostrar el gráfico
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuGraficos = new JMenu("Gráficos");
        menuBar.add(menuGraficos);

        JMenuItem menuItemProductosMasVendidos = new JMenuItem("Mostrar Productos Más Vendidos");
        menuGraficos.add(menuItemProductosMasVendidos);

        // Agrega un ActionListener al JMenuItem para mostrar el gráfico al hacer clic
        menuItemProductosMasVendidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarProductosMasVendidos(panelGrafico1); // Llama a la función para mostrar el gráfico en panelGrafico1
            }
        });

        // Crea y configura los JPanel (panelGrafico1 y panelGrafico2) aquí
        panelGrafico1 = new JPanel();
        panelGrafico1.setPreferredSize(new Dimension(600, 600)); // Establece el tamaño deseado para el gráfico
        // Agrega otros componentes o configuración si es necesario para el panelGrafico1

        panelGrafico2 = new JPanel();
        panelGrafico2.setPreferredSize(new Dimension(800, 600)); // Establece el tamaño deseado para el gráfico
        // Agrega otros componentes o configuración si es necesario para el panelGrafico2

        // Agrega los JPanel al JFrame utilizando un esquema de disposición (p. ej., GridLayout, BorderLayout)
        setLayout(new GridLayout(1, 2)); // Crea un GridLayout con 1 fila y 2 columnas
        add(panelGrafico1);
        add(panelGrafico2);

        // Configura el JFrame
        pack(); // Asegura el tamaño adecuado de los componentes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra el JFrame en la pantalla
        setVisible(true);
    }

    private void mostrarProductosMasVendidos(JPanel panel) {
        ProductosMasVendidos(panel); // Llama a la función con el JPanel deseado
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

    public static void main(String[] args) {
        // Crea y muestra el JFrame
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MiFrame();
            }
        });
    }
}
