/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vistaVentas;

import bo.ClienteBO;
import bo.VentaBO;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import reportes.GeneraReporte;
import rojerusan.RSNotifyFade;
import ajustes.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import necesario.RSFileChooser;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import reportes.ExcelGenerator;
import reportes.FakeDataProvider;
import reportes.GeneradorInformeContable;
import reportes.Grafico;

/**
 *
 * @author Kevscl
 */
public class principalReportes extends javax.swing.JPanel {

    /**
     * Creates new form principalReportes
     */
    VentaBO vbo = new VentaBO();
    ClienteBO cbo = new ClienteBO();
    GeneraReporte pdf = new GeneraReporte();
    EmailSender email = new EmailSender();

    public principalReportes() {
        initComponents();
        listarTotal();
        jPopupMenu1.add(menu);
        addEventKey();
    }

    private void addEventKey() {
        // Evento para la tecla F1
        KeyStroke f1 = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0, false);
        Action f1Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                // Código para el evento F1
                // Aquí puedes llamar a la función o método que deseas ejecutar cuando se presiona F1
                buscar();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f1, "F1");
        this.getActionMap().put("F1", f1Action);

        // Evento para la tecla F2
        KeyStroke f2 = KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0, false);
        Action f2Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                // Código para el evento F2
                // Aquí puedes llamar a la función o método que deseas ejecutar cuando se presiona F2
                pdf();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f2, "F2");
        this.getActionMap().put("F2", f2Action);

        // Evento para la tecla F3
        KeyStroke f3 = KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0, false);
        Action f3Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                // Código para el evento F3
                // Aquí puedes llamar a la función o método que deseas ejecutar cuando se presiona F3
                exportar();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f3, "F3");
        this.getActionMap().put("F3", f3Action);
    }

    public void listarTotal() {
        vbo.listarVentaReporteTotal(tbVentas);
    }

    public void listar() {
        //vbo.listarVenta(tbVentas);
        LocalDate fechaActual = LocalDate.now();

        // Convertir LocalDate a Date
        Date date = java.sql.Date.valueOf(fechaActual);

        // Definir el formato deseado
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");

        // Obtener la fecha formateada como una cadena
        String fecha = formatoFecha.format(date);
        //System.out.println(fecha);
        try {
            Date dateFormateado = formatoFecha.parse(fecha);
            dateFecha.setDate(dateFormateado);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Date fechaSeleccionada = dateFecha.getDate();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        String fechaFormateada = formato.format(fechaSeleccionada);
        String mensaje = vbo.listarVentaReporte(tbVentas, fechaFormateada);
    }

    public void ultimasVentas() {
        Date fechaSeleccionada = dateFecha.getDate();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        String fechaFormateada = formato.format(fechaSeleccionada);
        String mensaje = vbo.listarVentaReporte(tbVentas, fechaFormateada);
    }

    public void envia() {
        int fila = tbVentas.getSelectedRow();
        if (fila != -1) {
            int id = Integer.parseInt(tbVentas.getValueAt(fila, 0).toString());
            String correo = cbo.obtnerCorreoClienteVenta(id);
            //System.out.println("Correo " + correo);

            //Generamos el pdf
            String mensajePDF = pdf.generaPDF(id);
            if (!mensajePDF.equals("PDF GENERADO")) {
                new rojerusan.RSNotifyFade("¡ERROR!", "¡No se pudo generar el PDF!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.WARNING).setVisible(true);
                listar();
            } else {
                //Si se generó el pdf, se envía
                String nom = "Reporte " + id;
                String envioCorreo = email.enviarCorreoConArchivoAdjunto(correo, nom);
                System.out.println(envioCorreo);

                if (!envioCorreo.equals("CORREO ENVIADO EXITOSAMENTE.")) {
                    new rojerusan.RSNotifyFade("¡ERROR!", "¡Error al enviar el correo!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.WARNING).setVisible(true);
                } else {
                    new rojerusan.RSNotifyFade("¡SUCCESS!", "¡Correo enviado exitosamente!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.SUCCESS).setVisible(true);
                }
            }
            //Enviamos el reporte
            jPopupMenu1.setVisible(false);
        } else {
            listar();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JPanel();
        GeneraPDF = new RSMaterialComponent.RSButtonMaterialIconTwo();
        btnPDFEnvia1 = new RSMaterialComponent.RSButtonMaterialIconTwo();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        rSLabelTextIcon1 = new RSMaterialComponent.RSLabelTextIcon();
        rSLabelHora1 = new rojeru_san.rsdate.RSLabelHora();
        btnBuscar = new RSMaterialComponent.RSButtonMaterialIconTwo();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbVentas = new RSMaterialComponent.RSTableMetroCustom();
        txtBusqueda = new RSMaterialComponent.RSTextFieldIconOne();
        dateFecha = new newscomponents.RSDateChooserModern();
        btnPDF = new RSMaterialComponent.RSButtonMaterialIconTwo();
        btnExcel = new newscomponents.RSButtonFlat_new();
        rSButtonMaterialIconTwo1 = new RSMaterialComponent.RSButtonMaterialIconTwo();

        menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        GeneraPDF.setBackground(new java.awt.Color(153, 153, 0));
        GeneraPDF.setForeground(new java.awt.Color(153, 153, 0));
        GeneraPDF.setText("Genera PDF");
        GeneraPDF.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MAIL);
        GeneraPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GeneraPDFActionPerformed(evt);
            }
        });
        menu.add(GeneraPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, -1));

        btnPDFEnvia1.setBackground(new java.awt.Color(153, 153, 0));
        btnPDFEnvia1.setForeground(new java.awt.Color(153, 153, 0));
        btnPDFEnvia1.setText("Enviar PDF");
        btnPDFEnvia1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MAIL);
        btnPDFEnvia1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFEnvia1ActionPerformed(evt);
            }
        });
        menu.add(btnPDFEnvia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 200, -1));

        jPopupMenu1.getAccessibleContext().setAccessibleParent(menu);

        setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(153, 153, 0));

        rSLabelTextIcon1.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelTextIcon1.setText("REPORTE DE VENTAS");
        rSLabelTextIcon1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rSLabelTextIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.STORE);

        rSLabelHora1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(rSLabelTextIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSLabelTextIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnBuscar.setBackground(new java.awt.Color(153, 153, 0));
        btnBuscar.setText("CONSULTAR (F1)");
        btnBuscar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EVENT_AVAILABLE);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tbVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbVentas.setBackgoundHead(new java.awt.Color(153, 153, 0));
        tbVentas.setBackgoundHover(new java.awt.Color(153, 153, 0));
        tbVentas.setSelectionBackground(new java.awt.Color(153, 153, 0));
        tbVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbVentasMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbVentas);

        txtBusqueda.setForeground(new java.awt.Color(153, 153, 0));
        txtBusqueda.setBorderColor(new java.awt.Color(153, 153, 0));
        txtBusqueda.setColorIcon(new java.awt.Color(153, 153, 0));
        txtBusqueda.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        txtBusqueda.setPlaceholder("BUSCAR");
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });

        dateFecha.setBackground(new java.awt.Color(153, 153, 0));

        btnPDF.setBackground(new java.awt.Color(153, 153, 0));
        btnPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reportes/btnExcel.png"))); // NOI18N
        btnPDF.setText("(F2)");
        btnPDF.setBorderPainted(false);
        btnPDF.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PRINT);
        btnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFActionPerformed(evt);
            }
        });

        btnExcel.setBackground(new java.awt.Color(153, 153, 0));
        btnExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reportes/btnExcel.png"))); // NOI18N
        btnExcel.setText("(F3)");
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });

        rSButtonMaterialIconTwo1.setBackground(new java.awt.Color(153, 153, 0));
        rSButtonMaterialIconTwo1.setText("INFORME CONTABLE");
        rSButtonMaterialIconTwo1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);
        rSButtonMaterialIconTwo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMaterialIconTwo1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1083, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSButtonMaterialIconTwo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPDF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rSButtonMaterialIconTwo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tbVentasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVentasMouseReleased
        if (evt.isPopupTrigger()) {
            // Obtener la fila seleccionada
            int fila = tbVentas.getSelectedRow();
            // Verificar si se ha seleccionado una fila
            if (fila != -1) {
                // Mostrar el JPopupMenu en la posición del clic
                jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
            } else {
                //listar();
                jPopupMenu1.setVisible(false);
            }
        }
    }//GEN-LAST:event_tbVentasMouseReleased

    private void GeneraPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GeneraPDFActionPerformed
        int fila = tbVentas.getSelectedRow();
        if (fila != -1) {
            int id = Integer.parseInt(tbVentas.getValueAt(fila, 0).toString());
            GeneraReporte g = new GeneraReporte();
            try {
                g.generaPDFFile(id);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(principalReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
            jPopupMenu1.setVisible(false);
        } else {
            listar();
        }
    }//GEN-LAST:event_GeneraPDFActionPerformed

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed
        pdf();
    }//GEN-LAST:event_btnPDFActionPerformed

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        exportar();
    }//GEN-LAST:event_btnExcelActionPerformed

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        vbo.listarVentaReporteTotalBusqueda(tbVentas, txtBusqueda.getText());
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void btnPDFEnvia1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFEnvia1ActionPerformed
        envia();
    }//GEN-LAST:event_btnPDFEnvia1ActionPerformed

    private void rSButtonMaterialIconTwo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMaterialIconTwo1ActionPerformed
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fecha = dateFecha.getDate();
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
    }//GEN-LAST:event_rSButtonMaterialIconTwo1ActionPerformed

    public void pdf() {
        String tableName = "Reporte de ventas"; // Cambiar por el nombre de la tabla o vista que deseas mostrar
        String description = "Listado de ventas"; // Cambiar por una descripción adecuada

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
            GeneraReporte g = new GeneraReporte();
            String mensaje = g.generaPDFTabla(tableName, description, filePath, tbVentas);
            if (!mensaje.equals("PDF GENERADO")) {
                new rojerusan.RSNotifyFade("¡ERROR!", "¡Error al generar PDF!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.WARNING).setVisible(true);
            } else {
                new rojerusan.RSNotifyFade("¡SUCCESS!", "¡PDF Generado exitosamente!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.SUCCESS).setVisible(true);
            }
        }
    }

    public void exportar() {
        List<String> headers = FakeDataProvider.getTableHeaders(tbVentas);
        int tam = tbVentas.getRowCount();
        List<List<String>> content = FakeDataProvider.getTableContent(tbVentas, tam); // Obtén los datos de las primeras 20 filas

        ExcelGenerator excelGenerator = new ExcelGenerator();
        HSSFWorkbook workbook = excelGenerator.generateExcel("PERSONAS", headers, content);

        RSFileChooser fileChooser = new RSFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos Excel (*.xls)", "xls");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("GUARDAR ARCHIVO");
        if (fileChooser.showSaveDialog(this) == RSFileChooser.APPROVE_OPTION) {
            System.out.println(fileChooser.getSelectedFile().getAbsolutePath());

            File archivo = new File(fileChooser.getSelectedFile().getAbsolutePath());

            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    try {
                        OutputStream out = null;
                        if (getFileExtension(archivo)) {
                            out = new FileOutputStream(fileChooser.getSelectedFile().getAbsolutePath());
                        } else {
                            out = new FileOutputStream(fileChooser.getSelectedFile().getAbsolutePath() + ".xls");
                        }
                        workbook.write(out);
                        workbook.close();
                        out.flush();
                        out.close();
                    } catch (FileNotFoundException ex) {
                        showErrorAlert("Algo salió mal. El archivo que intenta sobreescribir se encuentra abierto, cierre el archivo e inténtelo de nuevo.");
                    } catch (IOException ex) {
                        showErrorAlert("Algo salió mal. No fue posible generar el archivo.");
                    }
                    return null;
                }

                @Override
                protected void done() {
                    showSuccessAlert("Archivo guardado con éxito.");
                }
            };
            worker.execute();
        }

    }

    private void showErrorAlert(String mensaje) {
        new rojerusan.RSNotifyFade("¡ERROR!", "¡" + mensaje + "!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.WARNING).setVisible(true);

    }

    // Método para mostrar un cuadro de diálogo de éxito
    private void showSuccessAlert(String mensaje) {
        new rojerusan.RSNotifyFade("¡SUCCESS!", "¡" + mensaje + "!", 5, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.SUCCESS).setVisible(true);
    }

    private boolean getFileExtension(File file) {
        String ext = null;
        String s = file.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }

        if (ext != null) {
            return true;
        } else {
            return false;
        }
    }

    public void buscar() {
        Date fechaSeleccionada = dateFecha.getDate();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        String fechaFormateada = formato.format(fechaSeleccionada);
        String mensaje = vbo.listarVentaReporte(tbVentas, fechaFormateada);
        if (!mensaje.equals("VENTAS ENCONTRADAS")) {
            new rojerusan.RSNotifyFade("¡ERROR!", "¡No hay ventas disponibles!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.WARNING).setVisible(true);
            listarTotal();
        } else {
            ultimasVentas();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonMaterialIconTwo GeneraPDF;
    private RSMaterialComponent.RSButtonMaterialIconTwo btnBuscar;
    private newscomponents.RSButtonFlat_new btnExcel;
    private RSMaterialComponent.RSButtonMaterialIconTwo btnPDF;
    private RSMaterialComponent.RSButtonMaterialIconTwo btnPDFEnvia1;
    private newscomponents.RSDateChooserModern dateFecha;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel menu;
    private RSMaterialComponent.RSButtonMaterialIconTwo rSButtonMaterialIconTwo1;
    private rojeru_san.rsdate.RSLabelHora rSLabelHora1;
    private RSMaterialComponent.RSLabelTextIcon rSLabelTextIcon1;
    private RSMaterialComponent.RSTableMetroCustom tbVentas;
    private RSMaterialComponent.RSTextFieldIconOne txtBusqueda;
    // End of variables declaration//GEN-END:variables
}
