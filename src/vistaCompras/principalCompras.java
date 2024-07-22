/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vistaCompras;

import ajustes.PanelListener;
import bo.ComprasBO;
import entity.Compras;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.KeyStroke;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import rojerusan.RSNotifyFade;
import static vistaCompras.actualizarCompra.dateFecha;

/**
 *
 * @author Kevscl
 */
public class principalCompras extends javax.swing.JPanel implements PanelListener {

    /**
     * Creates new form principalCompras
     */
    ComprasBO cbo = new ComprasBO();

    public principalCompras() {
        initComponents();
        listar();
        jPopupMenu1.add(menu);
        addEventKey();
    }

    private void addEventKey() {
        KeyStroke f1 = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0, false);
        Action f1Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                //agregar();
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
                //editar();
                eliminarTodo();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f3, "F3");
        this.getActionMap().put("F3", f3Action);

        //--------------------------------------------------------------------------------
        KeyStroke f4 = KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0, false);
        Action f4Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                //eliminar();
                editar();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f4, "F4");
        this.getActionMap().put("F4", f4Action);

        //--------------------------------------------------------------------------------
        KeyStroke f5 = KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0, false);
        Action f5Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                //listarF();
                eliminar();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f5, "F5");
        this.getActionMap().put("F5", f5Action);
    }

    public void onPanelClosed() {
        listar(); // Método para actualizar la lista de empleados
    }

    public void listar() {
        cbo.listarCompra(tbCompra);
    }

    public void agregar() {
        agregarCompra c = new agregarCompra();
        c.setPanelListener(this);
        c.setVisible(true);
    }

    public void editar() {
        int fila = tbCompra.getSelectedRow();
        if (fila != -1) {
            actualizarCompra c = new actualizarCompra();
            c.labelID.setText(tbCompra.getValueAt(fila, 0).toString());
            c.txtDescripcion.setText(tbCompra.getValueAt(fila, 1).toString());

            // Obtener el valor de fecha como un String desde la tabla
            String fechaString = tbCompra.getValueAt(fila, 2).toString();

            try {
                SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy/MM/dd");

                Date fecha = formatoEntrada.parse(fechaString);
                String fechaFormateada = formatoSalida.format(fecha);

                // Establecer la fecha formateada en tu componente dateFecha
                c.dateFecha.setDatoFecha(formatoSalida.parse(fechaFormateada));
            } catch (ParseException ex) {
                Logger.getLogger(actualizarCompra.class.getName()).log(Level.SEVERE, null, ex);
            }

            c.txtPrecio.setText(tbCompra.getValueAt(fila, 3).toString());
            c.setPanelListener(this);
            c.setVisible(true);
            tbCompra.clearSelection();
        } else {
            listar();
        }
        jPopupMenu1.setVisible(false);
    }

    public void eliminar() {
        int fila = tbCompra.getSelectedRow();
        if (fila != -1) {
            jPopupMenu1.setVisible(false);
            int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar?", "Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (resp == 0) {
                Compras c = new Compras();
                int id = Integer.parseInt(tbCompra.getValueAt(fila, 0).toString());
                String mensaje = cbo.eliminarCompra(id);
                if (!mensaje.equals("ELIMINADO CORRECTAMENTE")) {
                    new rojerusan.RSNotifyFade("¡ERROR!", "¡Error al eliminar el cliente!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.WARNING).setVisible(true);
                } else {
                    new rojerusan.RSNotifyFade("¡SUCCESS!", "¡Eliminado Correctamente!", 5, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.SUCCESS).setVisible(true);
                    listar();
                }
            }
            tbCompra.clearSelection();
        } else {
            listar();
        }
        tbCompra.clearSelection();
        jPopupMenu1.setVisible(false);
    }

    public void eliminarTodo() {
        int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar todos los registros?", "Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (resp == 0) {
            Compras c = new Compras();
            String mensaje = cbo.eliminarTodasLasCompras();
            if (!mensaje.equals("ELIMINADOS TODOS")) {
                new rojerusan.RSNotifyFade("¡ERROR!", "¡Error al eliminar los clientes!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.WARNING).setVisible(true);
            } else {
                new rojerusan.RSNotifyFade("¡SUCCESS!", "¡Registros eliminados con Éxito!", 5, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.SUCCESS).setVisible(true);
                listar();
            }
        }
        tbCompra.clearSelection();
        jPopupMenu1.setVisible(false);
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
        btnEliminar = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnEditar = new RSMaterialComponent.RSButtonMaterialIconOne();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        rSLabelTextIcon1 = new RSMaterialComponent.RSLabelTextIcon();
        rSLabelHora1 = new rojeru_san.rsdate.RSLabelHora();
        btnNuevo = new RSMaterialComponent.RSButtonMaterialIconShadow();
        rSTextFieldMaterialIcon1 = new RSMaterialComponent.RSTextFieldMaterialIcon();
        btnListar = new RSMaterialComponent.RSButtonMaterialIconShadow();
        btnEliminarTodo = new RSMaterialComponent.RSButtonMaterialIconShadow();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCompra = new RSMaterialComponent.RSTableMetroCustom();
        rSDateChooserModern1 = new newscomponents.RSDateChooserModern();

        menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEliminar.setBackground(new java.awt.Color(51, 102, 51));
        btnEliminar.setText("ELIMINAR (F5)");
        btnEliminar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DELETE);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        menu.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, -1, -1));

        btnEditar.setBackground(new java.awt.Color(51, 102, 51));
        btnEditar.setText("EDITAR (F4)");
        btnEditar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EDIT);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        menu.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        menu.getAccessibleContext().setAccessibleParent(jPopupMenu1);

        jPopupMenu1.getAccessibleContext().setAccessibleParent(menu);

        setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(51, 102, 51));

        rSLabelTextIcon1.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelTextIcon1.setText("Compras");
        rSLabelTextIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SHOPPING_CART);

        rSLabelHora1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(rSLabelTextIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSLabelTextIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnNuevo.setBackground(new java.awt.Color(51, 102, 51));
        btnNuevo.setText("NUEVO (F1)");
        btnNuevo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_SHOPPING_CART);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        rSTextFieldMaterialIcon1.setColorIcon(new java.awt.Color(51, 102, 51));
        rSTextFieldMaterialIcon1.setColorMaterial(new java.awt.Color(51, 102, 51));
        rSTextFieldMaterialIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        rSTextFieldMaterialIcon1.setPlaceholder("BUSCAR");

        btnListar.setBackground(new java.awt.Color(51, 102, 51));
        btnListar.setText("LISTAR (F2)");
        btnListar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.LIST);
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });

        btnEliminarTodo.setBackground(new java.awt.Color(51, 102, 51));
        btnEliminarTodo.setText("ELIMINAR TODO (F3)");
        btnEliminarTodo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DELETE);
        btnEliminarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTodoActionPerformed(evt);
            }
        });

        tbCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DESCRIPCIÓN", "FECHA", "TOTAL"
            }
        ));
        tbCompra.setBackgoundHead(new java.awt.Color(51, 102, 51));
        tbCompra.setBackgoundHover(new java.awt.Color(51, 102, 51));
        tbCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbCompraMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbCompra);

        rSDateChooserModern1.setBackground(new java.awt.Color(51, 102, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSTextFieldMaterialIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSDateChooserModern1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rSTextFieldMaterialIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rSDateChooserModern1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        agregar();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        listar();
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        editar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void tbCompraMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCompraMouseReleased
        int fila = tbCompra.getSelectedRow();
        // Verificar si se ha seleccionado una fila
        if (fila != -1) {
            // Mostrar el JPopupMenu en la posición del clic
            jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
        } else {
            listar();
        }
    }//GEN-LAST:event_tbCompraMouseReleased

    private void btnEliminarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTodoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarTodoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonMaterialIconOne btnEditar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnEliminar;
    private RSMaterialComponent.RSButtonMaterialIconShadow btnEliminarTodo;
    private RSMaterialComponent.RSButtonMaterialIconShadow btnListar;
    private RSMaterialComponent.RSButtonMaterialIconShadow btnNuevo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel menu;
    private newscomponents.RSDateChooserModern rSDateChooserModern1;
    private rojeru_san.rsdate.RSLabelHora rSLabelHora1;
    private RSMaterialComponent.RSLabelTextIcon rSLabelTextIcon1;
    private RSMaterialComponent.RSTextFieldMaterialIcon rSTextFieldMaterialIcon1;
    private RSMaterialComponent.RSTableMetroCustom tbCompra;
    // End of variables declaration//GEN-END:variables
}
