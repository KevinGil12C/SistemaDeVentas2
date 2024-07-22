package reportes;

/**
 *
 * @author Kevscl
 */
import bd.Conexion;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.filechooser.FileNameExtensionFilter;
import necesario.RSFileChooser;
import rojerusan.RSNotifyFade;

public class GeneradorInformeContable {

    // Método para generar el informe contable en PDF para un mes específico
    public static String generaPDF(Date fecha, String filePath) {
        String mensaje = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String primerDiaMes = obtenerPrimerDiaMesAnterior(fecha);
            String ultimoDiaMes = sdf.format(fecha);

            // Crear el documento PDF y el archivo de salida
            Document doc = new Document();
            FileOutputStream archivo = new FileOutputStream(filePath);
            PdfWriter.getInstance(doc, archivo);
            doc.open();

            // Obtener la conexión a la base de datos
            Connection connection = Conexion.getConnection();

            // Verificar si la conexión se estableció correctamente
            if (connection != null) {
                // Crear el encabezado y título del informe
                generarEncabezado(doc);
                Font fuenteNegritaAzul = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD, BaseColor.BLUE);
                Font fuenteNegritaAzul2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
                Paragraph titulo = new Paragraph();
                titulo.setAlignment(Element.ALIGN_CENTER);
                titulo.add(new Chunk("\nInforme Contable Mensual\n\n", fuenteNegritaAzul));
                doc.add(titulo);

                // Generar el informe contable consolidado
                generarInformeContable(connection, doc, primerDiaMes, ultimoDiaMes);

                // Cerrar la conexión a la base de datos
                connection.close();
            } else {
                mensaje = "No se pudo conectar a la base de datos";
            }

            // Cerrar el documento y el archivo
            doc.close();
            archivo.close();

            mensaje = "PDF GENERADO";

        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "Error al generar el informe contable.";
        }

        return mensaje;
    }

    private static void generarEncabezado(Document doc) throws DocumentException, IOException {
        Font fuenteNegritaAzul = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD, BaseColor.BLUE);
        Font fuenteNegritaAzul2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
        // Crear el encabezado
        // Crear el encabezado
        Image img = Image.getInstance(GeneraReporte.class.getClassLoader().getResource("imgPrincipal/logo-black.png"));
        Date date = new Date();
        Paragraph fecha = new Paragraph();
        Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
        fecha.add(Chunk.NEWLINE);
        fecha.add("\nFecha: " + new SimpleDateFormat("dd/MM/yyyy").format(date) + "\n");

        PdfPTable Encabezado = new PdfPTable(4);
        Encabezado.setWidthPercentage(100);
        Encabezado.getDefaultCell().setBorder(0);
        float[] ComunaEncabezado = new float[]{20f, 30f, 70f, 50f};
        Encabezado.setWidths(ComunaEncabezado);
        Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);

        Encabezado.addCell(img);
        String name = "Sistema de Ventas Leo";
        String frase = "¡Compra con Leo y viste con orgullo!";
        String tel = "7223357001";
        String dir = "EDOMEX";
        String ra = "Ropa Leo Uniformes Escolares";

        Encabezado.addCell("");
        Encabezado.addCell(name + "\n" + frase + "\nTel: " + tel + "\nDireccion: " + dir + "\n" + ra);
        Encabezado.addCell(fecha);
        doc.add(Encabezado);

        // Aplicar estilo de fuente a los párrafos
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        Font fuente = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLUE);

        // Agregar la tabla al documento PDF
        doc.add(table);

        // Agregar espacio después del encabezado
        doc.add(Chunk.NEWLINE);
    }

    private static String obtenerPrimerDiaMesAnterior(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.MONTH, -1);
//        cal.set(Calendar.DAY_OF_MONTH, fecha.getDay());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    private static void generarInformeContable(Connection connection, Document doc, String primerDiaMes, String ultimoDiaMes) throws Exception {
        try {
            // Crear el encabezado del informe contable
            Font fuenteNegritaAzul = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLUE);
            Paragraph encabezado = new Paragraph();
            encabezado.setAlignment(Element.ALIGN_CENTER);
            encabezado.add(new Chunk("Rango de fechas: " + primerDiaMes + " al " + ultimoDiaMes + "\n"));
            doc.add(encabezado);

            // Formatear las fechas al formato "yyyy-MM-dd"
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaInicio = sdf.format(sdf.parse(primerDiaMes));
            String fechaFin = sdf.format(sdf.parse(ultimoDiaMes));

            double ingresosTotales = 0;
            String sqlIngresos = "SELECT SUM(total) AS ingresos FROM VistaVenta WHERE fecha >= ? AND fecha > ?";
            try (PreparedStatement pstmtIngresos = connection.prepareStatement(sqlIngresos)) {
                pstmtIngresos.setString(1, fechaInicio);
                pstmtIngresos.setString(2, fechaFin);
                ResultSet rsIngresos = pstmtIngresos.executeQuery();
                if (rsIngresos.next()) {
                    ingresosTotales = rsIngresos.getDouble("ingresos");

                    // Formatear ingresosTotales para mostrar solo dos decimales
                    DecimalFormat df = new DecimalFormat("#.00");
                    String ingresosFormateados = df.format(ingresosTotales);
                    ingresosTotales = Double.parseDouble(ingresosFormateados);
                }
                rsIngresos.close();
            }

            // Obtener los gastos del rango de fechas (compras)
            double gastosTotales = 0;
            String sqlGastos = "SELECT SUM(total) AS gastos FROM COMPRAS WHERE fecha >= ? AND fecha < ?";
            try (PreparedStatement pstmtGastos = connection.prepareStatement(sqlGastos)) {
                pstmtGastos.setString(1, fechaInicio);
                pstmtGastos.setString(2, fechaFin);
                ResultSet rsGastos = pstmtGastos.executeQuery();
                if (rsGastos.next()) {
                    gastosTotales = rsGastos.getDouble("gastos");
                }
                rsGastos.close();
            }

            // Calcular el saldo (diferencia entre ingresos y gastos)
            double saldo = ingresosTotales - gastosTotales;

            // Recuperar la tabla de ingresos
            // Crear una tabla para los ingresos
            PdfPTable tablaIngresos = new PdfPTable(4); // 3 columnas para "ID Venta", "Fecha" y "Total"
            tablaIngresos.setWidthPercentage(100); // Ancho de la tabla en porcentaje del ancho del documento

            // Definir las cabeceras de las columnas
            Font fuenteCabecera = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
            PdfPCell celdaIdVenta = new PdfPCell(new Paragraph("ID Venta", fuenteCabecera));
            PdfPCell celdaCliente = new PdfPCell(new Paragraph("Cliente", fuenteCabecera));
            PdfPCell celdaFecha = new PdfPCell(new Paragraph("Fecha", fuenteCabecera));
            PdfPCell celdaTotal = new PdfPCell(new Paragraph("Total", fuenteCabecera));

            // Agregar las cabeceras a la tabla
            tablaIngresos.addCell(celdaIdVenta);
            tablaIngresos.addCell(celdaCliente);
            tablaIngresos.addCell(celdaFecha);
            tablaIngresos.addCell(celdaTotal);

            // Aquí ejecutas la consulta SQL para obtener los datos de ingresos y los agregas a la tabla
            String sqlTablaIngresos = "SELECT idVenta,nombreCompletoCliente, fecha, total FROM VistaVenta WHERE fecha >= ? AND fecha > ?";
            try (PreparedStatement pstmtTablaIngresos = connection.prepareStatement(sqlTablaIngresos)) {
                pstmtTablaIngresos.setString(1, fechaInicio);
                pstmtTablaIngresos.setString(2, fechaFin);
                ResultSet rsTablaIngresos = pstmtTablaIngresos.executeQuery();

                // Agregar los datos obtenidos de la base de datos a la tabla
                while (rsTablaIngresos.next()) {
                    int idVenta = rsTablaIngresos.getInt("idVenta");
                    String cliente = rsTablaIngresos.getString("nombreCompletoCliente");
                    String fechaVenta = rsTablaIngresos.getString("fecha");
                    double totalVenta = rsTablaIngresos.getDouble("total");

                    tablaIngresos.addCell(String.valueOf(idVenta));
                    tablaIngresos.addCell(cliente);
                    tablaIngresos.addCell(fechaVenta);
                    tablaIngresos.addCell("$" + String.format("%.2f", totalVenta)); // Formatear el total con dos decimales
                }

                rsTablaIngresos.close();
            }

            // Agregar la tabla de ingresos al documento (código existente)
            Paragraph tituloTablaIngresos = new Paragraph("\nTabla de Ingresos:\n\n", fuenteNegritaAzul);
            tituloTablaIngresos.setAlignment(Element.ALIGN_CENTER);
            doc.add(tituloTablaIngresos);
            doc.add(tablaIngresos);

            // Agregar la tabla de gastos al documento
            Paragraph tablaGastos = new Paragraph();
            tablaGastos.add(new Chunk("\n\nTabla de Gastos:\n", fuenteNegritaAzul));
            PdfPTable tablaGastosContent = new PdfPTable(4); // 4 columnas para "ID Compra", "Descripción", "Fecha" y "Total"
            tablaGastosContent.setWidthPercentage(100); // Ancho de la tabla en porcentaje del ancho del documento

            // Definir las cabeceras de las columnas para la tabla de gastos
            PdfPCell celdaIdCompra = new PdfPCell(new Paragraph("ID Compra", fuenteCabecera));
            PdfPCell celdaDescripcion = new PdfPCell(new Paragraph("Descripción", fuenteCabecera));
            PdfPCell celdaFechaCompra = new PdfPCell(new Paragraph("Fecha", fuenteCabecera));
            PdfPCell celdaTotalCompra = new PdfPCell(new Paragraph("Total", fuenteCabecera));

            // Agregar las cabeceras a la tabla de gastos
            tablaGastosContent.addCell(celdaIdCompra);
            tablaGastosContent.addCell(celdaDescripcion);
            tablaGastosContent.addCell(celdaFechaCompra);
            tablaGastosContent.addCell(celdaTotalCompra);

            // Aquí ejecutas la consulta SQL para obtener los datos de gastos y los agregas a la tabla de gastos
            String sqlTablaGastos = "SELECT idCompra, descripcion, fecha, total FROM COMPRAS WHERE fecha >= ? AND fecha < ?";
            try (PreparedStatement pstmtTablaGastos = connection.prepareStatement(sqlTablaGastos)) {
                pstmtTablaGastos.setString(1, fechaInicio);
                pstmtTablaGastos.setString(2, fechaFin);
                ResultSet rsTablaGastos = pstmtTablaGastos.executeQuery();

                // Agregar los datos obtenidos de la base de datos a la tabla de gastos
                while (rsTablaGastos.next()) {
                    int idCompra = rsTablaGastos.getInt("idCompra");
                    String descripcionCompra = rsTablaGastos.getString("descripcion");
                    String fechaCompra = rsTablaGastos.getString("fecha");
                    double totalCompra = rsTablaGastos.getDouble("total");

                    tablaGastosContent.addCell(String.valueOf(idCompra));
                    tablaGastosContent.addCell(descripcionCompra);
                    tablaGastosContent.addCell(fechaCompra);
                    tablaGastosContent.addCell("$" + String.format("%.2f", totalCompra)); // Formatear el total con dos decimales
                }

                rsTablaGastos.close();
            }

            tablaGastos.add(tablaGastosContent);
            doc.add(tablaGastos);

            // Crear fuentes personalizadas
            Font fuenteNormal = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
            Font fuenteNegrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Font fuenteResaltada = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLUE);

            // Agregar los resultados al informe contable con el estilo personalizado
            Paragraph resultados = new Paragraph();
            resultados.add("\n-----------------------------------------------\n");
            resultados.add("                 Resultados\n");
            resultados.add("-----------------------------------------------\n");
            resultados.add(new Chunk("\n", fuenteNegrita)); // Espacio en blanco

            // Ingresos Totales
            Chunk chunkIngresos = new Chunk("Ingresos Totales: $", fuenteNegrita);
            Chunk chunkIngresosTotal = new Chunk(String.format("%.2f", ingresosTotales), fuenteResaltada);
            resultados.add(chunkIngresos);
            resultados.add(chunkIngresosTotal);
            resultados.add(new Chunk("\n", fuenteNormal)); // Nueva línea

            // Gastos Totales
            Chunk chunkGastos = new Chunk("Gastos Totales: $", fuenteNegrita);
            Chunk chunkGastosTotal = new Chunk(String.format("%.2f", gastosTotales), fuenteResaltada);
            resultados.add(chunkGastos);
            resultados.add(chunkGastosTotal);
            resultados.add(new Chunk("\n", fuenteNormal)); // Nueva línea

            // Saldo
            Chunk chunkSaldo = new Chunk("Saldo: $", fuenteNegrita);
            Chunk chunkSaldoTotal = new Chunk(String.format("%.2f", saldo), fuenteResaltada);
            resultados.add(chunkSaldo);
            resultados.add(chunkSaldoTotal);
            resultados.add(new Chunk("\n", fuenteNormal)); // Nueva línea

            // Agregar los resultados al documento
            doc.add(resultados);

            // Aquí puedes agregar más información o detalles adicionales al informe contable si lo deseas.
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Método principal para probar la generación del informe para el 24/07/2023
    public static void main(String[] args) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = sdf.parse("24/07/2023");
            RSFileChooser fileChooser = new RSFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PDF (*.pdf)", "pdf");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setFileFilter(filter);
            fileChooser.setDialogTitle("GUARDAR ARCHIVO");
            if (fileChooser.showSaveDialog(null) == RSFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                if (!filePath.toLowerCase().endsWith(".pdf")) {
                    filePath += ".pdf"; // Agrega extensión .pdf si no está presente
                }
                GeneradorInformeContable g = new GeneradorInformeContable();
                String mensaje = g.generaPDF(fecha, filePath);
                if (!mensaje.equals("PDF GENERADO")) {
                    new rojerusan.RSNotifyFade("¡ERROR!", "¡Error al generar PDF!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.WARNING).setVisible(true);
                } else {
                    new rojerusan.RSNotifyFade("¡SUCCESS!", "¡PDF Generado exitosamente!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.SUCCESS).setVisible(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
