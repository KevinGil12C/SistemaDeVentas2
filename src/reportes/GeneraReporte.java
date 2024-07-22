package reportes;

import bd.Conexion;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import necesario.RSFileChooser;
import rojerusan.RSNotifyFade;

public class GeneraReporte {

    private static Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);

    public String generaPDF(int idVenta) {
        String aux = "";
        try {
            FileOutputStream archivo;
            File file = new File("C://reportes//Reporte " + idVenta + ".pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();

            // Obtener la conexión a la base de datos
            Connection connection = Conexion.getConnection();

            // Verificar si la conexión se estableció correctamente
            if (connection != null) {
                Font fuenteNegritaAzul = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD, BaseColor.BLUE);
                Font fuenteNegritaAzul2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
                // Crear el encabezado
                // Crear el encabezado
                Image img = Image.getInstance(GeneraReporte.class.getClassLoader().getResource("imgPrincipal/logo-black.png"));
                Date date = new Date();
                Paragraph fecha = new Paragraph();
                Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
                fecha.add(Chunk.NEWLINE);
                fecha.add("Factura: " + idVenta + "\nFecha: " + new SimpleDateFormat("MM/dd/yyyy").format(date) + "\n" + "Vendedor: \n" + getVendedorName(connection, idVenta));

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
                //Parte 2
                Paragraph cl = new Paragraph();
                cl.add(Chunk.NEWLINE);
                cl.add(new Chunk("Datos del cliente" + "\n\n", fuenteNegritaAzul2));
                doc.add(cl);

                PdfPTable tablacl = new PdfPTable(4);
                tablacl.setWidthPercentage(100);
                tablacl.getDefaultCell().setBorder(0);
                float[] Comunacl = new float[]{30f, 60f, 15f, 20f};
                tablacl.setWidths(Comunacl);
                tablacl.setHorizontalAlignment(Element.ALIGN_LEFT);
                //Buscamos el cliente
                PdfPCell cl1 = new PdfPCell(new Phrase("RFC:", negrita));
                PdfPCell cl2 = new PdfPCell(new Phrase("Nombre: ", negrita));
                PdfPCell cl3 = new PdfPCell(new Phrase(""));
                PdfPCell cl4 = new PdfPCell(new Phrase(""));

                String clien[] = getClienteData(connection, idVenta);
                cl1.setBorder(0);
                cl2.setBorder(0);
                cl3.setBorder(0);
                cl4.setBorder(0);
                tablacl.addCell(cl1);
                tablacl.addCell(cl2);
                tablacl.addCell(cl3);
                tablacl.addCell(cl4);
                tablacl.addCell(clien[0]);
                tablacl.addCell(clien[1]);
                tablacl.addCell("");
                tablacl.addCell("");

                doc.add(tablacl);
                // Crear tabla para el encabezado
                PdfPTable encabezadoTable = new PdfPTable(1);
                encabezadoTable.setWidthPercentage(100);
                PdfPCell imgCell = new PdfPCell(img, true);
                imgCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                imgCell.setBorder(Rectangle.NO_BORDER);
                encabezadoTable.addCell(imgCell);

                // Obtener los datos de la venta utilizando la vista VistaDetalleVenta
                String sql = "SELECT cantidad, nameProducto, pUnitario,total FROM VistaDetalleVenta WHERE idVenta = ?";
                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setInt(1, idVenta);
                    ResultSet rs = pstmt.executeQuery();

                    // Crear el párrafo para mostrar los detalles de la venta
                    Paragraph detalleVenta = new Paragraph();
                    Paragraph detalleVenta2 = new Paragraph();
                    detalleVenta.setAlignment(Element.ALIGN_CENTER); // Centrar el párrafo
                    detalleVenta.add(new Chunk("\n\nDetalle de la Venta\n", fuenteNegritaAzul));
                    detalleVenta2.add(new Chunk("ID Venta: " + idVenta + "\n\n", fuenteNegritaAzul2));

                    // Crear la tabla para mostrar los productos de la venta
                    PdfPTable tablaProductos = new PdfPTable(4);
                    tablaProductos.setWidthPercentage(100);
                    tablaProductos.getDefaultCell().setBorder(0);

                    // Crear el estilo de fuente con color blanco
                    Font fuenteBlanca = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.WHITE);

                    // Crear las celdas de encabezado y aplicar el estilo de fuente blanco
                    PdfPCell header1 = new PdfPCell(new Paragraph("Cant:", fuenteBlanca));
                    PdfPCell header2 = new PdfPCell(new Paragraph("Nombre:", fuenteBlanca));
                    PdfPCell header3 = new PdfPCell(new Paragraph("P/Unitario:", fuenteBlanca));
                    PdfPCell header4 = new PdfPCell(new Paragraph("P/Total:", fuenteBlanca));

                    // Establecer el fondo oscuro para las celdas de encabezado
                    header1.setBackgroundColor(BaseColor.DARK_GRAY);
                    header2.setBackgroundColor(BaseColor.DARK_GRAY);
                    header3.setBackgroundColor(BaseColor.DARK_GRAY);
                    header4.setBackgroundColor(BaseColor.DARK_GRAY);

                    // Agregar las celdas de encabezado a la tabla
                    tablaProductos.addCell(header1);
                    tablaProductos.addCell(header2);
                    tablaProductos.addCell(header3);
                    tablaProductos.addCell(header4);

                    // Agregar los productos de la venta a la tabla
                    while (rs.next()) {
                        String cant = rs.getString("cantidad");
                        String nomb = rs.getString("nameProducto");
                        String puni = rs.getString("pUnitario");
                        String ptot = rs.getString("total");

                        tablaProductos.addCell(cant);
                        tablaProductos.addCell(nomb);
                        tablaProductos.addCell(puni);
                        tablaProductos.addCell(ptot);
                    }

                    // Cerrar el result set y liberar recursos
                    rs.close();

                    // Agregar el párrafo y la tabla al documento PDF
                    doc.add(detalleVenta);
                    doc.add(detalleVenta2);
                    doc.add(tablaProductos);
                    // Parte 3 - Obtener el Subtotal, IVA, Total y Método de Pago desde la vista VistaDetalleVenta y la tabla VENTA
                    String subtotal = null;
                    String iva = null;
                    String total = null;
                    String metodoPago = null;

                    // Obtener el Subtotal, IVA, Total y Método de Pago en una sola consulta
                    String sqlT = "SELECT SUM(total/1.16) AS subtotal, SUM(total) * 0.16 AS iva, SUM(total) AS total FROM VistaDetalleVenta WHERE idVenta = ?";
                    try (PreparedStatement pstmtVenta = connection.prepareStatement(sqlT)) {
                        pstmtVenta.setInt(1, idVenta);
                        ResultSet rsVenta = pstmtVenta.executeQuery();
                        if (rsVenta.next()) {
                            subtotal = String.format("%.2f", rsVenta.getFloat("subtotal"));
                            iva = String.format("%.2f", rsVenta.getFloat("iva"));
                            total = String.format("%.2f", rsVenta.getFloat("total"));

                        }
                        rsVenta.close();
                    }

                    // Obtener el Método de Pago desde la tabla VENTA
                    String sqlTam = "SELECT mPago FROM VENTA WHERE idVenta = ?";
                    try (PreparedStatement pstmtVenta = connection.prepareStatement(sqlTam)) {
                        pstmtVenta.setInt(1, idVenta);
                        ResultSet rsVenta = pstmtVenta.executeQuery();
                        if (rsVenta.next()) {
                            metodoPago = rsVenta.getString("mPago");
                        }
                        rsVenta.close();
                    }
                    // Crear el párrafo para mostrar la Parte 3
                    Font fuenteNegrita2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);

                    Paragraph info = new Paragraph();
                    info.add(Chunk.NEWLINE);
                    info.add(new Chunk("\n\nSubtotal: $" + subtotal + "\n", fuenteNegrita2));
                    info.add(new Chunk("IVA 16%: $" + iva + "\n", fuenteNegrita2));
                    info.add(new Chunk("Total: $" + total + "\n", fuenteNegrita2));
                    info.add(new Chunk("M. Pago: " + metodoPago + "\n", fuenteNegrita2));
                    info.setAlignment(Element.ALIGN_RIGHT);
                    doc.add(info);

                    //Parte 4
                    Paragraph firma = new Paragraph();
                    firma.add(Chunk.NEWLINE);
                    firma.add("\n\nFirma: \n\n");
                    firma.add("-------------------------");
                    firma.setAlignment(Element.ALIGN_CENTER);
                    doc.add(firma);

                    //Parte 5
                    Paragraph mensaje = new Paragraph();
                    mensaje.add(Chunk.NEWLINE);
                    mensaje.add("Gracias por su Compra");
                    mensaje.setAlignment(Element.ALIGN_CENTER);
                    doc.add(mensaje);

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // Cerrar la conexión a la base de datos
                connection.close();
            } else {
                System.out.println("No se pudo conectar a la base de datos");
            }

            // Cerrar el documento y el archivo
            doc.close();
            archivo.close();
            aux = "PDF GENERADO";
            //System.out.println("El informe PDF ha sido generado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return aux;
    }

    public String generaPDFFile(int idVenta) throws FileNotFoundException {
        String aux = "";
        try {
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

                FileOutputStream archivo;
                File file = new File(filePath);
                archivo = new FileOutputStream(file);
                Document doc = new Document();
                PdfWriter.getInstance(doc, archivo);
                doc.open();

                // Obtener la conexión a la base de datos
                Connection connection = Conexion.getConnection();

                // Verificar si la conexión se estableció correctamente
                if (connection != null) {
                    Font fuenteNegritaAzul = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD, BaseColor.BLUE);
                    Font fuenteNegritaAzul2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
                    // Crear el encabezado
                    // Crear el encabezado
                    Image img = Image.getInstance(GeneraReporte.class.getClassLoader().getResource("imgPrincipal/logo-black.png"));
                    Date date = new Date();
                    Paragraph fecha = new Paragraph();
                    Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
                    fecha.add(Chunk.NEWLINE);
                    fecha.add("Factura: " + idVenta + "\nFecha: " + new SimpleDateFormat("MM/dd/yyyy").format(date) + "\n" + "Vendedor: \n" + getVendedorName(connection, idVenta));

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
                    //Parte 2
                    Paragraph cl = new Paragraph();
                    cl.add(Chunk.NEWLINE);
                    cl.add(new Chunk("Datos del cliente" + "\n\n", fuenteNegritaAzul2));
                    doc.add(cl);

                    PdfPTable tablacl = new PdfPTable(4);
                    tablacl.setWidthPercentage(100);
                    tablacl.getDefaultCell().setBorder(0);
                    float[] Comunacl = new float[]{30f, 60f, 15f, 20f};
                    tablacl.setWidths(Comunacl);
                    tablacl.setHorizontalAlignment(Element.ALIGN_LEFT);
                    //Buscamos el cliente
                    PdfPCell cl1 = new PdfPCell(new Phrase("RFC:", negrita));
                    PdfPCell cl2 = new PdfPCell(new Phrase("Nombre: ", negrita));
                    PdfPCell cl3 = new PdfPCell(new Phrase(""));
                    PdfPCell cl4 = new PdfPCell(new Phrase(""));

                    String clien[] = getClienteData(connection, idVenta);
                    cl1.setBorder(0);
                    cl2.setBorder(0);
                    cl3.setBorder(0);
                    cl4.setBorder(0);
                    tablacl.addCell(cl1);
                    tablacl.addCell(cl2);
                    tablacl.addCell(cl3);
                    tablacl.addCell(cl4);
                    tablacl.addCell(clien[0]);
                    tablacl.addCell(clien[1]);
                    tablacl.addCell("");
                    tablacl.addCell("");

                    doc.add(tablacl);
                    // Crear tabla para el encabezado
                    PdfPTable encabezadoTable = new PdfPTable(1);
                    encabezadoTable.setWidthPercentage(100);
                    PdfPCell imgCell = new PdfPCell(img, true);
                    imgCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    imgCell.setBorder(Rectangle.NO_BORDER);
                    encabezadoTable.addCell(imgCell);

                    // Obtener los datos de la venta utilizando la vista VistaDetalleVenta
                    String sql = "SELECT cantidad, nameProducto, pUnitario,total FROM VistaDetalleVenta WHERE idVenta = ?";
                    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                        pstmt.setInt(1, idVenta);
                        ResultSet rs = pstmt.executeQuery();

                        // Crear el párrafo para mostrar los detalles de la venta
                        Paragraph detalleVenta = new Paragraph();
                        Paragraph detalleVenta2 = new Paragraph();
                        detalleVenta.setAlignment(Element.ALIGN_CENTER); // Centrar el párrafo
                        detalleVenta.add(new Chunk("\n\nDetalle de la Venta\n", fuenteNegritaAzul));
                        detalleVenta2.add(new Chunk("ID Venta: " + idVenta + "\n\n", fuenteNegritaAzul2));

                        // Crear la tabla para mostrar los productos de la venta
                        PdfPTable tablaProductos = new PdfPTable(4);
                        tablaProductos.setWidthPercentage(100);
                        tablaProductos.getDefaultCell().setBorder(0);

                        // Crear el estilo de fuente con color blanco
                        Font fuenteBlanca = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.WHITE);

                        // Crear las celdas de encabezado y aplicar el estilo de fuente blanco
                        PdfPCell header1 = new PdfPCell(new Paragraph("Cant:", fuenteBlanca));
                        PdfPCell header2 = new PdfPCell(new Paragraph("Nombre:", fuenteBlanca));
                        PdfPCell header3 = new PdfPCell(new Paragraph("P/Unitario:", fuenteBlanca));
                        PdfPCell header4 = new PdfPCell(new Paragraph("P/Total:", fuenteBlanca));

                        // Establecer el fondo oscuro para las celdas de encabezado
                        header1.setBackgroundColor(BaseColor.DARK_GRAY);
                        header2.setBackgroundColor(BaseColor.DARK_GRAY);
                        header3.setBackgroundColor(BaseColor.DARK_GRAY);
                        header4.setBackgroundColor(BaseColor.DARK_GRAY);

                        // Agregar las celdas de encabezado a la tabla
                        tablaProductos.addCell(header1);
                        tablaProductos.addCell(header2);
                        tablaProductos.addCell(header3);
                        tablaProductos.addCell(header4);

                        // Agregar los productos de la venta a la tabla
                        while (rs.next()) {
                            String cant = rs.getString("cantidad");
                            String nomb = rs.getString("nameProducto");
                            String puni = rs.getString("pUnitario");
                            String ptot = rs.getString("total");

                            tablaProductos.addCell(cant);
                            tablaProductos.addCell(nomb);
                            tablaProductos.addCell(puni);
                            tablaProductos.addCell(ptot);
                        }

                        // Cerrar el result set y liberar recursos
                        rs.close();

                        // Agregar el párrafo y la tabla al documento PDF
                        doc.add(detalleVenta);
                        doc.add(detalleVenta2);
                        doc.add(tablaProductos);
                        // Parte 3 - Obtener el Subtotal, IVA, Total y Método de Pago desde la vista VistaDetalleVenta y la tabla VENTA
                        String subtotal = null;
                        String iva = null;
                        String total = null;
                        String metodoPago = null;

                        // Obtener el Subtotal, IVA, Total y Método de Pago en una sola consulta
                        String sqlT = "SELECT SUM(total/1.16) AS subtotal, SUM(total) * 0.16 AS iva, SUM(total) AS total FROM VistaDetalleVenta WHERE idVenta = ?";
                        try (PreparedStatement pstmtVenta = connection.prepareStatement(sqlT)) {
                            pstmtVenta.setInt(1, idVenta);
                            ResultSet rsVenta = pstmtVenta.executeQuery();
                            if (rsVenta.next()) {
                                subtotal = String.format("%.2f", rsVenta.getFloat("subtotal"));
                                iva = String.format("%.2f", rsVenta.getFloat("iva"));
                                total = String.format("%.2f", rsVenta.getFloat("total"));

                            }
                            rsVenta.close();
                        }

                        // Obtener el Método de Pago desde la tabla VENTA
                        String sqlTam = "SELECT mPago FROM VENTA WHERE idVenta = ?";
                        try (PreparedStatement pstmtVenta = connection.prepareStatement(sqlTam)) {
                            pstmtVenta.setInt(1, idVenta);
                            ResultSet rsVenta = pstmtVenta.executeQuery();
                            if (rsVenta.next()) {
                                metodoPago = rsVenta.getString("mPago");
                            }
                            rsVenta.close();
                        }
                        // Crear el párrafo para mostrar la Parte 3
                        Font fuenteNegrita2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);

                        Paragraph info = new Paragraph();
                        info.add(Chunk.NEWLINE);
                        info.add(new Chunk("\n\nSubtotal: $" + subtotal + "\n", fuenteNegrita2));
                        info.add(new Chunk("IVA 16%: $" + iva + "\n", fuenteNegrita2));
                        info.add(new Chunk("Total: $" + total + "\n", fuenteNegrita2));
                        info.add(new Chunk("M. Pago: " + metodoPago + "\n", fuenteNegrita2));
                        info.setAlignment(Element.ALIGN_RIGHT);
                        doc.add(info);

                        //Parte 4
                        Paragraph firma = new Paragraph();
                        firma.add(Chunk.NEWLINE);
                        firma.add("\n\nFirma: \n\n");
                        firma.add("-------------------------");
                        firma.setAlignment(Element.ALIGN_CENTER);
                        doc.add(firma);

                        //Parte 5
                        Paragraph mensaje = new Paragraph();
                        mensaje.add(Chunk.NEWLINE);
                        mensaje.add("Gracias por su Compra");
                        mensaje.setAlignment(Element.ALIGN_CENTER);
                        doc.add(mensaje);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    // Cerrar la conexión a la base de datos
                    connection.close();
                } else {
                    System.out.println("No se pudo conectar a la base de datos");
                }

                // Cerrar el documento y el archivo
                // Cerrar el documento y el archivo
                doc.close();
                archivo.close();
                aux = "PDF GENERADO";

                // Abrir el PDF en el navegador
                File pdfFile = new File(filePath);
                if (pdfFile.exists() && Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("No se puede abrir el archivo PDF o no es compatible con el escritorio.");
                }

                // Mostrar notificación de éxito
                new RSNotifyFade("¡SUCCESS!", "¡PDF Generado exitosamente!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.SUCCESS).setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aux;
    }

    public String generaPDFopen(int idVenta) {
        String aux = "";
        try {
            FileOutputStream archivo;
            File file = new File("C://reportes//Reporte " + idVenta + ".pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();

            // Obtener la conexión a la base de datos
            Connection connection = Conexion.getConnection();

            // Verificar si la conexión se estableció correctamente
            if (connection != null) {
                Font fuenteNegritaAzul = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD, BaseColor.BLUE);
                Font fuenteNegritaAzul2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
                // Crear el encabezado
                // Crear el encabezado
                Image img = Image.getInstance(GeneraReporte.class.getClassLoader().getResource("imgPrincipal/logo-black.png"));
                Date date = new Date();
                Paragraph fecha = new Paragraph();
                Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
                fecha.add(Chunk.NEWLINE);
                fecha.add("Factura: " + idVenta + "\nFecha: " + new SimpleDateFormat("MM/dd/yyyy").format(date) + "\n" + "Vendedor: \n" + getVendedorName(connection, idVenta));

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
                //Parte 2
                Paragraph cl = new Paragraph();
                cl.add(Chunk.NEWLINE);
                cl.add(new Chunk("Datos del cliente" + "\n\n", fuenteNegritaAzul2));
                doc.add(cl);

                PdfPTable tablacl = new PdfPTable(4);
                tablacl.setWidthPercentage(100);
                tablacl.getDefaultCell().setBorder(0);
                float[] Comunacl = new float[]{30f, 60f, 15f, 20f};
                tablacl.setWidths(Comunacl);
                tablacl.setHorizontalAlignment(Element.ALIGN_LEFT);
                //Buscamos el cliente
                PdfPCell cl1 = new PdfPCell(new Phrase("RFC:", negrita));
                PdfPCell cl2 = new PdfPCell(new Phrase("Nombre: ", negrita));
                PdfPCell cl3 = new PdfPCell(new Phrase(""));
                PdfPCell cl4 = new PdfPCell(new Phrase(""));

                String clien[] = getClienteData(connection, idVenta);
                cl1.setBorder(0);
                cl2.setBorder(0);
                cl3.setBorder(0);
                cl4.setBorder(0);
                tablacl.addCell(cl1);
                tablacl.addCell(cl2);
                tablacl.addCell(cl3);
                tablacl.addCell(cl4);
                tablacl.addCell(clien[0]);
                tablacl.addCell(clien[1]);
                tablacl.addCell("");
                tablacl.addCell("");

                doc.add(tablacl);
                // Crear tabla para el encabezado
                PdfPTable encabezadoTable = new PdfPTable(1);
                encabezadoTable.setWidthPercentage(100);
                PdfPCell imgCell = new PdfPCell(img, true);
                imgCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                imgCell.setBorder(Rectangle.NO_BORDER);
                encabezadoTable.addCell(imgCell);

                // Obtener los datos de la venta utilizando la vista VistaDetalleVenta
                String sql = "SELECT cantidad, nameProducto, pUnitario,total FROM VistaDetalleVenta WHERE idVenta = ?";
                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setInt(1, idVenta);
                    ResultSet rs = pstmt.executeQuery();

                    // Crear el párrafo para mostrar los detalles de la venta
                    Paragraph detalleVenta = new Paragraph();
                    Paragraph detalleVenta2 = new Paragraph();
                    detalleVenta.setAlignment(Element.ALIGN_CENTER); // Centrar el párrafo
                    detalleVenta.add(new Chunk("\n\nDetalle de la Venta\n", fuenteNegritaAzul));
                    detalleVenta2.add(new Chunk("ID Venta: " + idVenta + "\n\n", fuenteNegritaAzul2));

                    // Crear la tabla para mostrar los productos de la venta
                    PdfPTable tablaProductos = new PdfPTable(4);
                    tablaProductos.setWidthPercentage(100);
                    tablaProductos.getDefaultCell().setBorder(0);

                    // Crear el estilo de fuente con color blanco
                    Font fuenteBlanca = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.WHITE);

                    // Crear las celdas de encabezado y aplicar el estilo de fuente blanco
                    PdfPCell header1 = new PdfPCell(new Paragraph("Cant:", fuenteBlanca));
                    PdfPCell header2 = new PdfPCell(new Paragraph("Nombre:", fuenteBlanca));
                    PdfPCell header3 = new PdfPCell(new Paragraph("P/Unitario:", fuenteBlanca));
                    PdfPCell header4 = new PdfPCell(new Paragraph("P/Total:", fuenteBlanca));

                    // Establecer el fondo oscuro para las celdas de encabezado
                    header1.setBackgroundColor(BaseColor.DARK_GRAY);
                    header2.setBackgroundColor(BaseColor.DARK_GRAY);
                    header3.setBackgroundColor(BaseColor.DARK_GRAY);
                    header4.setBackgroundColor(BaseColor.DARK_GRAY);

                    // Agregar las celdas de encabezado a la tabla
                    tablaProductos.addCell(header1);
                    tablaProductos.addCell(header2);
                    tablaProductos.addCell(header3);
                    tablaProductos.addCell(header4);

                    // Agregar los productos de la venta a la tabla
                    while (rs.next()) {
                        String cant = rs.getString("cantidad");
                        String nomb = rs.getString("nameProducto");
                        String puni = rs.getString("pUnitario");
                        String ptot = rs.getString("total");

                        tablaProductos.addCell(cant);
                        tablaProductos.addCell(nomb);
                        tablaProductos.addCell(puni);
                        tablaProductos.addCell(ptot);
                    }

                    // Cerrar el result set y liberar recursos
                    rs.close();

                    // Agregar el párrafo y la tabla al documento PDF
                    doc.add(detalleVenta);
                    doc.add(detalleVenta2);
                    doc.add(tablaProductos);
                    // Parte 3 - Obtener el Subtotal, IVA, Total y Método de Pago desde la vista VistaDetalleVenta y la tabla VENTA
                    String subtotal = null;
                    String iva = null;
                    String total = null;
                    String metodoPago = null;

                    // Obtener el Subtotal, IVA, Total y Método de Pago en una sola consulta
                    String sqlT = "SELECT SUM(total/1.16) AS subtotal, SUM(total) * 0.16 AS iva, SUM(total) AS total FROM VistaDetalleVenta WHERE idVenta = ?";
                    try (PreparedStatement pstmtVenta = connection.prepareStatement(sqlT)) {
                        pstmtVenta.setInt(1, idVenta);
                        ResultSet rsVenta = pstmtVenta.executeQuery();
                        if (rsVenta.next()) {
                            subtotal = String.format("%.2f", rsVenta.getFloat("subtotal"));
                            iva = String.format("%.2f", rsVenta.getFloat("iva"));
                            total = String.format("%.2f", rsVenta.getFloat("total"));

                        }
                        rsVenta.close();
                    }

                    // Obtener el Método de Pago desde la tabla VENTA
                    String sqlTam = "SELECT mPago FROM VENTA WHERE idVenta = ?";
                    try (PreparedStatement pstmtVenta = connection.prepareStatement(sqlTam)) {
                        pstmtVenta.setInt(1, idVenta);
                        ResultSet rsVenta = pstmtVenta.executeQuery();
                        if (rsVenta.next()) {
                            metodoPago = rsVenta.getString("mPago");
                        }
                        rsVenta.close();
                    }
                    // Crear el párrafo para mostrar la Parte 3
                    Font fuenteNegrita2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);

                    Paragraph info = new Paragraph();
                    info.add(Chunk.NEWLINE);
                    info.add(new Chunk("\n\nSubtotal: $" + subtotal + "\n", fuenteNegrita2));
                    info.add(new Chunk("IVA 16%: $" + iva + "\n", fuenteNegrita2));
                    info.add(new Chunk("Total: $" + total + "\n", fuenteNegrita2));
                    info.add(new Chunk("M. Pago: " + metodoPago + "\n", fuenteNegrita2));
                    info.setAlignment(Element.ALIGN_RIGHT);
                    doc.add(info);

                    //Parte 4
                    Paragraph firma = new Paragraph();
                    firma.add(Chunk.NEWLINE);
                    firma.add("\n\nFirma: \n\n");
                    firma.add("-------------------------");
                    firma.setAlignment(Element.ALIGN_CENTER);
                    doc.add(firma);

                    //Parte 5
                    Paragraph mensaje = new Paragraph();
                    mensaje.add(Chunk.NEWLINE);
                    mensaje.add("Gracias por su Compra");
                    mensaje.setAlignment(Element.ALIGN_CENTER);
                    doc.add(mensaje);

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // Cerrar la conexión a la base de datos
                connection.close();
            } else {
                System.out.println("No se pudo conectar a la base de datos");
            }

            // Cerrar el documento y el archivo
            doc.close();
            archivo.close();
            aux = "PDF GENERADO";

            // Abrir el PDF en el navegador
            File pdfFile = new File("C://reportes//Reporte " + idVenta + ".pdf");
            if (pdfFile.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(pdfFile);
            } else {
                System.out.println("No se puede abrir el archivo PDF o no es compatible con el escritorio.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return aux;
    }

    // Obtener el nombre del vendedor
    public static String getVendedorName(Connection connection, int idVenta) throws SQLException {
        String vendedorName = null;
        String sql = "SELECT e.usuario FROM VENTA v "
                + "INNER JOIN EMPLEADO e ON v.idEmpleado = e.idEmpleado "
                + "WHERE v.idVenta = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idVenta);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                vendedorName = rs.getString("usuario");
            }
            rs.close();
        }
        return vendedorName;
    }

    // Obtener el nombre y RFC del cliente
    public static String[] getClienteData(Connection connection, int idVenta) throws SQLException {
        String[] clienteData = new String[2];
        String sql = "SELECT c.nomCliente || ' ' || c.ape1Cliente || ' ' || c.ape2Cliente AS nombreCliente, c.rfcCliente "
                + "FROM VENTA v "
                + "INNER JOIN CLIENTE c ON v.idCliente = c.idCliente "
                + "WHERE v.idVenta = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idVenta);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                clienteData[0] = rs.getString("rfcCliente");
                clienteData[1] = rs.getString("nombreCliente");
            }
            rs.close();
        }
        return clienteData;
    }

    //--------------------------------------------------
    public String generaPDFTabla(String tableName, String description, String filePath, JTable tabla) {
        String mensaje = "";
        try {
            File file = new File(filePath);
            FileOutputStream archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();

            generarEncabezado(doc, tableName, description);
            generarContenido(doc, tabla);

            // Cerrar el documento y el archivo
            doc.close();
            archivo.close();
            mensaje = "PDF GENERADO";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    private static void generarEncabezado(Document doc, String tableName, String description) throws DocumentException, IOException {
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
        Paragraph tableNameParagraph = new Paragraph(tableName, fuente);
        Paragraph descriptionParagraph = new Paragraph(description, fuente);

        // Agregar los párrafos centrados en la tabla
        tableNameParagraph.setAlignment(Element.ALIGN_CENTER);
        descriptionParagraph.setAlignment(Element.ALIGN_CENTER);
        table.addCell(tableNameParagraph);
        table.addCell(descriptionParagraph);

        // Agregar la tabla al documento PDF
        doc.add(table);

        // Agregar espacio después del encabezado
        doc.add(Chunk.NEWLINE);
    }

    private static void generarContenido(Document doc, JTable tabla) throws DocumentException, IOException {
        // Obtener el número de filas y columnas de la tabla
        int numRows = tabla.getRowCount();
        int numCols = tabla.getColumnCount();

        // Crear una tabla con el número de columnas igual a la cantidad de columnas en el JTable
        PdfPTable table = new PdfPTable(numCols);

        // Aplicar estilo a la tabla (bordes, color de fondo, etc.)
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);

        // Agregar encabezados a la tabla
        for (int j = 0; j < numCols; j++) {
            PdfPCell cell = new PdfPCell(new Phrase(tabla.getColumnName(j), new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));

            // Aplicar estilo a las celdas de encabezado
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

            table.addCell(cell);
        }

        // Agregar datos a la tabla
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                PdfPCell cell = new PdfPCell(new Phrase(String.valueOf(tabla.getValueAt(i, j))));

                // Aplicar estilo a las celdas de datos
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

                table.addCell(cell);
            }
        }

        // Agregar la tabla al documento PDF
        doc.add(table);
    }

}
