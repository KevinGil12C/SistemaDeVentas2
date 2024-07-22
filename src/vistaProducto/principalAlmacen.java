/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vistaProducto;

import ajustes.PanelListener;
import bo.ProductoBO;
import entity.Producto;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import necesario.RSFileChooser;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import reportes.ExcelGenerator;
import reportes.FakeDataProvider;
import rojerusan.RSNotifyFade;

/**
 *
 * @author Kevscl
 */
public class principalAlmacen extends javax.swing.JPanel implements PanelListener {

    private ProductoBO pbo = new ProductoBO();

    public principalAlmacen() {
        initComponents();
        listar();
        jPopupMenu1.add(menu);
        addEventKey();
    }

    private void addEventKey() {

        /*
        * NUEVO
        * LISTAR
        * ELIMINAR TODO
        * CATEGORIA
        * EXPORTAR
        * EDITAR
        * ELIMINAR
         */
        KeyStroke f1 = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0, false);
        Action f1Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                agregar();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f1, "F1");
        this.getActionMap().put("F1", f1Action);

        //---------------------------------------------------------------------------
        KeyStroke f2 = KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0, false);
        Action f2Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                listar();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f2, "F2");
        this.getActionMap().put("F2", f2Action);

        //------------------------------------------------------------------------------
        KeyStroke f3 = KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0, false);
        Action f3Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                eliminarTodo();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f3, "F3");
        this.getActionMap().put("F3", f3Action);

        //--------------------------------------------------------------------------------
        KeyStroke f4 = KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0, false);
        Action f4Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                categoria();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f4, "F4");
        this.getActionMap().put("F4", f4Action);

        //--------------------------------------------------------------------------------
        KeyStroke f5 = KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0, false);
        Action f5Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                exportar();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f5, "F5");
        this.getActionMap().put("F5", f5Action);

        //--------------------------------------------------------------------------------
        // Acción para la tecla F6
        KeyStroke f6 = KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0, false);
        Action f6Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                // Aquí colocas el código que deseas ejecutar cuando se presione la tecla F6
                // Por ejemplo, llamar a un método para editar campos
                editar();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f6, "F6");
        this.getActionMap().put("F6", f6Action);

        // Acción para la tecla F7
        KeyStroke f7 = KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0, false);
        Action f7Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                // Aquí colocas el código que deseas ejecutar cuando se presione la tecla F7
                // Por ejemplo, llamar a un método para actualizar los datos
                eliminar();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f7, "F7");
        this.getActionMap().put("F7", f7Action);

    }

    public void onPanelClosed() {
        listar(); // Método para actualizar la lista de empleados
    }

    public void listar() {
        pbo.listarProducto(tbProducto);
    }

    public void editar() {
        int fila = tbProducto.getSelectedRow();
        if (fila != -1) {
            formActualizarProducto c = new formActualizarProducto();
            c.labelID.setText(tbProducto.getValueAt(fila, 0).toString());
            c.txtProducto.setText(tbProducto.getValueAt(fila, 1).toString());
            c.comboCat.setSelectedItem(tbProducto.getValueAt(fila, 2).toString());
            c.comboTam.setSelectedItem(tbProducto.getValueAt(fila, 3).toString());
            c.txtPrecio.setText(tbProducto.getValueAt(fila, 4).toString());
            c.txtCantidad.setText(tbProducto.getValueAt(fila, 5).toString());
            // Asignar el valor seleccionado en el campo comboGenero
            c.setPanelListener(this);
            c.setVisible(true);
            tbProducto.clearSelection();
        } else {
            listar();
        }
    }

    public void eliminar() {
        int fila = tbProducto.getSelectedRow();
        if (fila != -1) {
            int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar?", "Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (resp == 0) {
                Producto p = new Producto();
                int id = Integer.parseInt(tbProducto.getValueAt(fila, 0).toString());
                String mensaje = pbo.eliminarProducto(id);
                if (!mensaje.equals("ELIMINADO CORRECTAMENTE")) {
                    new rojerusan.RSNotifyFade("¡ERROR!", "¡Error al eliminar el cliente!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.WARNING).setVisible(true);
                } else {
                    new rojerusan.RSNotifyFade("¡SUCCESS!", "¡Eliminado Correctamente!", 5, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.SUCCESS).setVisible(true);
                    listar();
                }
            }
            tbProducto.clearSelection();
        } else {
            listar();
        }
    }

    public void eliminarTodo() {
        int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar todos los registros?", "Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (resp == 0) {
            Producto p = new Producto();
            String mensaje = pbo.eliminarTodosLosProductos();
            if (!mensaje.equals("ELIMINADOS TODOS")) {
                new rojerusan.RSNotifyFade("¡ERROR!", "¡Error al eliminar los clientes!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.WARNING).setVisible(true);
            } else {
                new rojerusan.RSNotifyFade("¡SUCCESS!", "¡Registros eliminados con Éxito!", 5, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.SUCCESS).setVisible(true);
                listar();
            }
        }
        tbProducto.clearSelection();
    }

    public void categoria() {
        formCategorias c = new formCategorias();
        c.setPanelListener(this);
        c.setVisible(true);
    }

    public void agregar() {
        formAgregarPro p = new formAgregarPro();
        p.setPanelListener(this);
        p.setVisible(true);
    }

    public void exportar() {
        List<String> headers = FakeDataProvider.getTableHeaders(tbProducto);
        int tam = tbProducto.getRowCount();
        List<List<String>> content = FakeDataProvider.getTableContent(tbProducto, tam); // Obtén los datos de las primeras 20 filas

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JPanel();
        btnELiminar = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnEditar = new RSMaterialComponent.RSButtonMaterialIconOne();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        rSLabelTextIcon1 = new RSMaterialComponent.RSLabelTextIcon();
        rSLabelHora1 = new rojeru_san.rsdate.RSLabelHora();
        btnNuevo = new RSMaterialComponent.RSButtonMaterialIconOne();
        txtBusqueda = new RSMaterialComponent.RSTextFieldIconTwo();
        btnCategoria = new RSMaterialComponent.RSButtonMaterialIconTwo();
        btnExportar = new RSMaterialComponent.RSButtonMaterialIconTwo();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProducto = new RSMaterialComponent.RSTableMetroCustom();
        btnEliminarTodo = new RSMaterialComponent.RSButtonMaterialIconTwo();
        btnListar = new RSMaterialComponent.RSButtonMaterialIconTwo();

        menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnELiminar.setBackground(new java.awt.Color(153, 0, 153));
        btnELiminar.setText("ELIMINAR (F)");
        btnELiminar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DELETE);
        btnELiminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnELiminarActionPerformed(evt);
            }
        });
        menu.add(btnELiminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, -1, -1));

        btnEditar.setBackground(new java.awt.Color(153, 0, 153));
        btnEditar.setText("EDITAR (F6)");
        btnEditar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.UPDATE);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        menu.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPopupMenu1.getAccessibleContext().setAccessibleParent(menu);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(153, 0, 153));

        rSLabelTextIcon1.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelTextIcon1.setText("ALMÁCEN Y CONFIGURACIÓN DE PRODUCTOS");
        rSLabelTextIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ARCHIVE);

        rSLabelHora1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(rSLabelTextIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSLabelTextIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnNuevo.setBackground(new java.awt.Color(153, 0, 153));
        btnNuevo.setText("NUEVO (F1)");
        btnNuevo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_BOX);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        txtBusqueda.setForeground(new java.awt.Color(153, 153, 153));
        txtBusqueda.setBorderColor(new java.awt.Color(153, 153, 153));
        txtBusqueda.setColorIcon(new java.awt.Color(153, 153, 153));
        txtBusqueda.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        txtBusqueda.setPlaceholder("BUSCAR");
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });

        btnCategoria.setBackground(new java.awt.Color(153, 0, 153));
        btnCategoria.setText("CATEGORÍAS (F4)");
        btnCategoria.setAutoscrolls(true);
        btnCategoria.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.LANGUAGE);
        btnCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoriaActionPerformed(evt);
            }
        });

        btnExportar.setBackground(new java.awt.Color(153, 0, 153));
        btnExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reportes/btnExcel.png"))); // NOI18N
        btnExportar.setText("EXPORTAR (F5)");
        btnExportar.setBorderPainted(false);
        btnExportar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.FOLDER);
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        tbProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "PRODUCTO", "CATEGORÍA", "TAMAÑO", "PRECIO", "STOCK"
            }
        ));
        tbProducto.setAutoscrolls(false);
        tbProducto.setBackgoundHead(new java.awt.Color(153, 0, 153));
        tbProducto.setBackgoundHover(new java.awt.Color(153, 0, 153));
        tbProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbProductoMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbProducto);

        btnEliminarTodo.setBackground(new java.awt.Color(153, 0, 153));
        btnEliminarTodo.setText("ELIMINAR TODO (F3)");
        btnEliminarTodo.setBorderPainted(false);
        btnEliminarTodo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btnEliminarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTodoActionPerformed(evt);
            }
        });

        btnListar.setBackground(new java.awt.Color(153, 0, 153));
        btnListar.setText("LISTAR (F2)");
        btnListar.setBorderPainted(false);
        btnListar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
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
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarTodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoriaActionPerformed
        categoria();
    }//GEN-LAST:event_btnCategoriaActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        agregar();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        exportar();
    }//GEN-LAST:event_btnExportarActionPerformed

    private void btnEliminarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTodoActionPerformed
        eliminarTodo();

    }//GEN-LAST:event_btnEliminarTodoActionPerformed

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        listar();
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        editar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnELiminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnELiminarActionPerformed
        eliminar();
        listar();
    }//GEN-LAST:event_btnELiminarActionPerformed

    private void tbProductoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductoMouseReleased
        if (evt.isPopupTrigger()) {
            // Obtener la fila seleccionada
            int fila = tbProducto.getSelectedRow();
            // Verificar si se ha seleccionado una fila
            if (fila != -1) {
                // Mostrar el JPopupMenu en la posición del clic
                jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
            } else {
                listar();
                jPopupMenu1.setVisible(false);
            }
        }
    }//GEN-LAST:event_tbProductoMouseReleased

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        pbo.listarProductoBusqueda(tbProducto, txtBusqueda.getText());
    }//GEN-LAST:event_txtBusquedaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonMaterialIconTwo btnCategoria;
    private RSMaterialComponent.RSButtonMaterialIconOne btnELiminar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnEditar;
    private RSMaterialComponent.RSButtonMaterialIconTwo btnEliminarTodo;
    private RSMaterialComponent.RSButtonMaterialIconTwo btnExportar;
    private RSMaterialComponent.RSButtonMaterialIconTwo btnListar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnNuevo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel menu;
    private rojeru_san.rsdate.RSLabelHora rSLabelHora1;
    private RSMaterialComponent.RSLabelTextIcon rSLabelTextIcon1;
    private RSMaterialComponent.RSTableMetroCustom tbProducto;
    private RSMaterialComponent.RSTextFieldIconTwo txtBusqueda;
    // End of variables declaration//GEN-END:variables
}
