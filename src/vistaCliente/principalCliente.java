/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vistaCliente;

import ajustes.PanelListener;
import bo.ClienteBO;
import entity.Cliente;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
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
public class principalCliente extends javax.swing.JPanel implements PanelListener {

    ClienteBO cbo = new ClienteBO();
    Cliente c = new Cliente();

    public principalCliente() {
        initComponents();
        listar();
        jPopupMenu1.add(menu);
        addEventKey();
    }

    private void addEventKey() {

        KeyStroke f1 = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0, false);
        Action f1Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                //Agregar
                agregar();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f1, "F1");
        this.getActionMap().put("F1", f1Action);

        //---------------------------------------------------------------------------
        KeyStroke f2 = KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0, false);
        Action f2Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                //eliminarTodo();
                eliminarToto();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f2, "F2");
        this.getActionMap().put("F2", f2Action);

        //------------------------------------------------------------------------------
        KeyStroke f3 = KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0, false);
        Action f3Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                //Listar
                listar();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f3, "F3");
        this.getActionMap().put("F3", f3Action);

        //--------------------------------------------------------------------------------
        KeyStroke f4 = KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0, false);
        Action f4Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f4, "F4");
        this.getActionMap().put("F4", f4Action);

        //--------------------------------------------------------------------------------
        KeyStroke f5 = KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0, false);
        Action f5Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                //Eliminar
                eliminar();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f5, "F5");
        this.getActionMap().put("F5", f5Action);

        KeyStroke f6 = KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0, false);
        Action f6Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                // Código para el evento F6
                // Aquí puedes llamar a la función o método que deseas ejecutar cuando se presiona F6
                pdf();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f6, "F6");
        this.getActionMap().put("F6", f6Action);

        KeyStroke f7 = KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0, false);
        Action f7Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                // Código para el evento F7
                // Aquí puedes llamar a la función o método que deseas ejecutar cuando se presiona F7
                exportar();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f7, "F7");
        this.getActionMap().put("F7", f7Action);

    }

    public void onPanelClosed() {
        listar(); // Método para actualizar la lista de empleados
    }

    public void listar() {
        cbo.listarCliente(tbCliente);
        txtBusqueda.setText("");
    }

    public static void realizarAlgoDespuesDeCerrarFormActualizar() {
        principalCliente principal = new principalCliente();
        principal.listar();
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
        btnEditar = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnEliminar = new RSMaterialComponent.RSButtonMaterialIconOne();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        rSLabelTextIcon1 = new RSMaterialComponent.RSLabelTextIcon();
        rSLabelHora1 = new rojeru_san.rsdate.RSLabelHora();
        btnNuevo = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnEliminarTodo = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnListar = new RSMaterialComponent.RSButtonMaterialIconOne();
        txtBusqueda = new RSMaterialComponent.RSTextFieldIconOne();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCliente = new RSMaterialComponent.RSTableMetroCustom();
        btnPDF = new RSMaterialComponent.RSButtonMaterialIconTwo();
        btnExcel = new newscomponents.RSButtonFlat_new();

        menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEditar.setBackground(new java.awt.Color(20, 164, 77));
        btnEditar.setText("EDITAR (F4)");
        btnEditar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EDIT);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        menu.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btnEliminar.setBackground(new java.awt.Color(20, 164, 77));
        btnEliminar.setText("ELIMINAR (F5)");
        btnEliminar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DELETE);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        menu.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, -1, -1));

        jPopupMenu1.getAccessibleContext().setAccessibleParent(menu);

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(20, 164, 77));

        rSLabelTextIcon1.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelTextIcon1.setText("Cliente");
        rSLabelTextIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PEOPLE);

        rSLabelHora1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(rSLabelTextIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSLabelTextIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnNuevo.setBackground(new java.awt.Color(20, 164, 77));
        btnNuevo.setText("NUEVO (F1)");
        btnNuevo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEliminarTodo.setBackground(new java.awt.Color(20, 164, 77));
        btnEliminarTodo.setText("ELIMINAR TODO (F2)");
        btnEliminarTodo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DELETE_FOREVER);
        btnEliminarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTodoActionPerformed(evt);
            }
        });

        btnListar.setBackground(new java.awt.Color(20, 164, 77));
        btnListar.setText("LISTAR (F3)");
        btnListar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.LIBRARY_BOOKS);
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });

        txtBusqueda.setBorderColor(new java.awt.Color(20, 164, 77));
        txtBusqueda.setColorIcon(new java.awt.Color(20, 164, 77));
        txtBusqueda.setPlaceholder("BUSCAR");
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });

        tbCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbCliente.setBackgoundHead(new java.awt.Color(20, 164, 77));
        tbCliente.setBackgoundHover(new java.awt.Color(20, 164, 77));
        tbCliente.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        tbCliente.setColorSecundaryText(new java.awt.Color(0, 0, 0));
        tbCliente.setSelectionBackground(new java.awt.Color(20, 164, 77));
        tbCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbClienteMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbCliente);

        btnPDF.setBackground(new java.awt.Color(20, 164, 77));
        btnPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reportes/btnExcel.png"))); // NOI18N
        btnPDF.setText("(F6)");
        btnPDF.setBorderPainted(false);
        btnPDF.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PRINT);
        btnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFActionPerformed(evt);
            }
        });

        btnExcel.setBackground(new java.awt.Color(20, 164, 77));
        btnExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reportes/btnExcel.png"))); // NOI18N
        btnExcel.setText("(F7)");
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPDF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        editar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        agregar();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEliminarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTodoActionPerformed
        eliminarToto();
    }//GEN-LAST:event_btnEliminarTodoActionPerformed

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        listar();
    }//GEN-LAST:event_btnListarActionPerformed

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        cbo.listarClienteBusqueda(tbCliente, txtBusqueda.getText());
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void tbClienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbClienteMouseReleased
        int fila = tbCliente.getSelectedRow();
        // Verificar si se ha seleccionado una fila
        if (fila != -1) {
            // Mostrar el JPopupMenu en la posición del clic
            jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
        } else {
            listar();
        }
    }//GEN-LAST:event_tbClienteMouseReleased

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed
        pdf();
    }//GEN-LAST:event_btnPDFActionPerformed

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        exportar();
    }//GEN-LAST:event_btnExcelActionPerformed

    public void agregar() {
        formAgregar c = new formAgregar();
        c.setPanelListener(this);
        c.setAlwaysOnTop(true); // Mantener la ventana emergente en la capa superior
        c.setVisible(true);
        jPopupMenu1.setVisible(false);
    }

    public void eliminarToto() {
        int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar todos los registros?", "Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (resp == 0) {
            Cliente c = new Cliente();
            String mensaje = cbo.eliminarTodosLosClientes();
            if (!mensaje.equals("ELIMINADOS TODOS")) {
                new rojerusan.RSNotifyFade("¡ERROR!", "¡Error al eliminar los clientes!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.WARNING).setVisible(true);
            } else {
                new rojerusan.RSNotifyFade("¡SUCCESS!", "¡Registros eliminados con Éxito!", 5, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.SUCCESS).setVisible(true);
                listar();
            }
        }
        tbCliente.clearSelection();
        jPopupMenu1.setVisible(false);
    }

    public void editar() {
        int fila = tbCliente.getSelectedRow();
        if (fila != -1) {
            formActualizar c = new formActualizar();
            c.labelID.setText(tbCliente.getValueAt(fila, 0).toString());
            c.txtNom.setText(tbCliente.getValueAt(fila, 1).toString());
            c.txtAp.setText(tbCliente.getValueAt(fila, 2).toString());
            c.txtAm.setText(tbCliente.getValueAt(fila, 3).toString());
            c.txtRfc.setText(tbCliente.getValueAt(fila, 4).toString());
            c.txtCorreo.setText(tbCliente.getValueAt(fila, 6).toString());
            c.txtTel.setText(tbCliente.getValueAt(fila, 7).toString());

            // Asignar el valor seleccionado en el campo comboGenero
            String genero = tbCliente.getValueAt(fila, 5).toString();
            DefaultComboBoxModel<String> comboModel = (DefaultComboBoxModel<String>) c.comboGenero.getModel();
            comboModel.setSelectedItem(genero);
            c.setPanelListener(this);
            c.setAlwaysOnTop(true); // Mantener la ventana emergente en la capa superior
            c.setVisible(true);
            tbCliente.clearSelection();
        } else {
            listar();
        }
        jPopupMenu1.setVisible(false);
    }

    public void eliminar() {
        int fila = tbCliente.getSelectedRow();
        if (fila != -1) {
            int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar?", "Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (resp == 0) {
                Cliente c = new Cliente();
                int id = Integer.parseInt(tbCliente.getValueAt(fila, 0).toString());
                String mensaje = cbo.eliminarCliente(id);
                if (!mensaje.equals("ELIMINADO CORRECTAMENTE")) {
                    new rojerusan.RSNotifyFade("¡ERROR!", "¡Error al eliminar el cliente!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.WARNING).setVisible(true);
                } else {
                    new rojerusan.RSNotifyFade("¡SUCCESS!", "¡Eliminado Correctamente!", 5, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.SUCCESS).setVisible(true);
                    listar();
                }
            }
            tbCliente.clearSelection();

        } else {
            listar();
        }
        jPopupMenu1.setVisible(false);
    }

    public void pdf() {
        String tableName = "Clientes"; // Cambiar por el nombre de la tabla o vista que deseas mostrar
        String description = "Listado de clientes"; // Cambiar por una descripción adecuada

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
            String mensaje = g.generaPDFTabla(tableName, description, filePath, tbCliente);
            if (!mensaje.equals("PDF GENERADO")) {
                new rojerusan.RSNotifyFade("¡ERROR!", "¡Error al generar PDF!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.WARNING).setVisible(true);
            } else {
                new rojerusan.RSNotifyFade("¡SUCCESS!", "¡PDF Generado exitosamente!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.SUCCESS).setVisible(true);
            }
        }
    }

    public void exportar() {
        List<String> headers = FakeDataProvider.getTableHeaders(tbCliente);
        int tam = tbCliente.getRowCount();
        List<List<String>> content = FakeDataProvider.getTableContent(tbCliente, tam); // Obtén los datos de las primeras 20 filas

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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonMaterialIconOne btnEditar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnEliminar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnEliminarTodo;
    private newscomponents.RSButtonFlat_new btnExcel;
    private RSMaterialComponent.RSButtonMaterialIconOne btnListar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnNuevo;
    private RSMaterialComponent.RSButtonMaterialIconTwo btnPDF;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel menu;
    private rojeru_san.rsdate.RSLabelHora rSLabelHora1;
    private RSMaterialComponent.RSLabelTextIcon rSLabelTextIcon1;
    private RSMaterialComponent.RSTableMetroCustom tbCliente;
    private RSMaterialComponent.RSTextFieldIconOne txtBusqueda;
    // End of variables declaration//GEN-END:variables
}
