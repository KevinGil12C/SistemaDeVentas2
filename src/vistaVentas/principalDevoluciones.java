package vistaVentas;

import bo.DevolucionBO;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import reportes.GeneraReporte;
import rojerusan.RSNotifyFade;

/**
 *
 * @author Kevscl
 */
public class principalDevoluciones extends javax.swing.JPanel {

    /**
     * Creates new form principalDevoluciones
     */
    GeneraReporte g = new GeneraReporte();
    DevolucionBO dbo = new DevolucionBO();

    public principalDevoluciones() {
        initComponents();
        listar();
        addEventKey();
    }

    private void addEventKey() {
        // Acción para la tecla F1
        KeyStroke f1 = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0, false);
        Action f1Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                // Aquí colocas el código que deseas ejecutar cuando se presione la tecla F1
                // Por ejemplo, llamar a un método para mostrar ayuda o instrucciones
                buscar();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f1, "F1");
        this.getActionMap().put("F1", f1Action);

        // Acción para la tecla F2
        KeyStroke f2 = KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0, false);
        Action f2Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                // Aquí colocas el código que deseas ejecutar cuando se presione la tecla F2
                pdf();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f2, "F2");
        this.getActionMap().put("F2", f2Action);

        // Acción para la tecla ESC
        KeyStroke f3 = KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0, false);
        Action escAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                // Aquí colocas el código que deseas ejecutar cuando se presione la tecla ESC
                exportar();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f3, "F3");
        this.getActionMap().put("F3", escAction);

    }
    
    public void listar() {
        String mensaje = dbo.listarDevoluciones(tbDevolucion);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        rSLabelTextIcon1 = new RSMaterialComponent.RSLabelTextIcon();
        rSLabelHora1 = new rojeru_san.rsdate.RSLabelHora();
        btnBuscar = new RSMaterialComponent.RSButtonMaterialIconTwo();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDevolucion = new RSMaterialComponent.RSTableMetroCustom();
        btnExcel = new newscomponents.RSButtonFlat_new();
        btnPDF = new RSMaterialComponent.RSButtonMaterialIconTwo();
        txtBusqeuda = new RSMaterialComponent.RSTextFieldIconOne();
        dateFecha = new newscomponents.RSDateChooser();

        setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 153, 0));

        rSLabelTextIcon1.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelTextIcon1.setText("DEVOLUCIONES");
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

        btnBuscar.setBackground(new java.awt.Color(0, 153, 0));
        btnBuscar.setText("CONSULTAR (F1)");
        btnBuscar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EVENT_AVAILABLE);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tbDevolucion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbDevolucion.setBackgoundHead(new java.awt.Color(0, 153, 0));
        tbDevolucion.setBackgoundHover(new java.awt.Color(0, 153, 0));
        tbDevolucion.setSelectionBackground(new java.awt.Color(0, 153, 0));
        jScrollPane1.setViewportView(tbDevolucion);

        btnExcel.setBackground(new java.awt.Color(0, 153, 0));
        btnExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reportes/btnExcel.png"))); // NOI18N
        btnExcel.setText("(F3)");
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });

        btnPDF.setBackground(new java.awt.Color(0, 153, 0));
        btnPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reportes/btnExcel.png"))); // NOI18N
        btnPDF.setText("(F2)");
        btnPDF.setBorderPainted(false);
        btnPDF.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PRINT);
        btnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFActionPerformed(evt);
            }
        });

        txtBusqeuda.setForeground(new java.awt.Color(0, 153, 0));
        txtBusqeuda.setBorderColor(new java.awt.Color(0, 153, 0));
        txtBusqeuda.setColorIcon(new java.awt.Color(0, 153, 0));
        txtBusqeuda.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        txtBusqeuda.setPlaceholder("BUSCAR");
        txtBusqeuda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusqeudaKeyReleased(evt);
            }
        });

        dateFecha.setBackground(new java.awt.Color(0, 153, 0));
        dateFecha.setBgColor(new java.awt.Color(0, 153, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtBusqeuda, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBusqeuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPDF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        exportar();
    }//GEN-LAST:event_btnExcelActionPerformed

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed
        pdf();
    }//GEN-LAST:event_btnPDFActionPerformed

    private void txtBusqeudaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusqeudaKeyReleased
        dbo.listarDevolucionBusqueda(tbDevolucion, txtBusqeuda.getText());
    }//GEN-LAST:event_txtBusqeudaKeyReleased

    public void pdf() {
        String tableName = "Devoluciones"; // Cambiar por el nombre de la tabla o vista que deseas mostrar
        String description = "Listado de Productos devueltos"; // Cambiar por una descripción adecuada

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

            String mensaje = g.generaPDFTabla(tableName, description, filePath, tbDevolucion);
            if (!mensaje.equals("PDF GENERADO")) {
                new rojerusan.RSNotifyFade("¡ERROR!", "¡Error al generar PDF!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.WARNING).setVisible(true);
            } else {
                new rojerusan.RSNotifyFade("¡SUCCESS!", "¡PDF Generado exitosamente!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.SUCCESS).setVisible(true);
            }
        }
    }
    
    public void exportar() {
        List<String> headers = FakeDataProvider.getTableHeaders(tbDevolucion);
        int tam = tbDevolucion.getRowCount();
        List<List<String>> content = FakeDataProvider.getTableContent(tbDevolucion, tam); // Obtén los datos de las primeras 20 filas

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

    public void buscar(){
        Date fechaSeleccionada = dateFecha.getDate();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        String fechaFormateada = formato.format(fechaSeleccionada);
        String mensaje = dbo.listarDevolucionesFecha(tbDevolucion, fechaFormateada);
        if (!mensaje.equals("HAY DEVOLUCIONES")) {
            new rojerusan.RSNotifyFade("¡ERROR!", "¡No hay devoluciones disponibles!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.WARNING).setVisible(true);
            listar();
        } else {
            dbo.listarDevolucionesFecha(tbDevolucion, fechaFormateada);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonMaterialIconTwo btnBuscar;
    private newscomponents.RSButtonFlat_new btnExcel;
    private RSMaterialComponent.RSButtonMaterialIconTwo btnPDF;
    private newscomponents.RSDateChooser dateFecha;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private rojeru_san.rsdate.RSLabelHora rSLabelHora1;
    private RSMaterialComponent.RSLabelTextIcon rSLabelTextIcon1;
    private RSMaterialComponent.RSTableMetroCustom tbDevolucion;
    private RSMaterialComponent.RSTextFieldIconOne txtBusqeuda;
    // End of variables declaration//GEN-END:variables
}
