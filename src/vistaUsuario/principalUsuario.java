package vistaUsuario;

/**
 *
 * @author Kevscl
 */
import ajustes.PanelListener;
import bo.*;
import entity.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import rojerusan.RSNotifyFade;

public class principalUsuario extends javax.swing.JPanel implements PanelListener{

    /**
     * Creates new form principalUsuario
     */
    EmpleadoBO ebo = new EmpleadoBO();
    Empleado emp = new Empleado();

    public principalUsuario() {
        initComponents();
        listar();
        jPopupMenu1.add(menu);
        addEventKey();
    }

    public void listar() {
        ebo.listarEmpleado(tbUsuarios);
    }

    public void onPanelClosed() {
        listar(); // Método para actualizar la lista de empleados
    }

    private void addEventKey() {

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
                eliminarTodo();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f2, "F2");
        this.getActionMap().put("F2", f2Action);

        //------------------------------------------------------------------------------
        KeyStroke f3 = KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0, false);
        Action f3Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f3, "F3");
        this.getActionMap().put("F3", f3Action);

        //--------------------------------------------------------------------------------
        KeyStroke f4 = KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0, false);
        Action f4Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                eliminar();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f4, "F4");
        this.getActionMap().put("F4", f4Action);

        //--------------------------------------------------------------------------------
        KeyStroke f5 = KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0, false);
        Action f5Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                listarF();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f5, "F5");
        this.getActionMap().put("F5", f5Action);
    }

    public void agregar() {
        agregarUsuario c = new agregarUsuario();
        c.setPanelListener(this);
        c.setVisible(true);
    }

    public void eliminarTodo() {
        int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar todos los registros?", "Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (resp == 0) {
            Cliente c = new Cliente();
            String mensaje = ebo.eliminarTodosEmpleados();
            if (!mensaje.equals("ELIMINADOS TODOS")) {
                new rojerusan.RSNotifyFade("¡ERROR!", "¡Error al eliminar los clientes!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.WARNING).setVisible(true);
            } else {
                new rojerusan.RSNotifyFade("¡SUCCESS!", "¡Registros eliminados con Éxito!", 5, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.SUCCESS).setVisible(true);
                listar();
            }
        }
        tbUsuarios.clearSelection();
       jPopupMenu1.setVisible(false);
    }

    public void editar() {
        int fila = tbUsuarios.getSelectedRow();
        if (fila != -1) {
            jPopupMenu1.setVisible(false);
            actualizarUsuario c = new actualizarUsuario();
            c.labelID.setText(tbUsuarios.getValueAt(fila, 0).toString());
            c.txtNombre.setText(tbUsuarios.getValueAt(fila, 1).toString());
            c.txtAP.setText(tbUsuarios.getValueAt(fila, 2).toString());
            c.txtAM.setText(tbUsuarios.getValueAt(fila, 3).toString());
            c.txtUsuario.setText(tbUsuarios.getValueAt(fila, 4).toString());
            c.txtPass.setText(tbUsuarios.getValueAt(fila, 5).toString());
            c.txtCorreo.setText(tbUsuarios.getValueAt(fila, 6).toString());
            c.setPanelListener(this);
            c.setVisible(true);
            tbUsuarios.clearSelection();
        } else {
            listar();
        }
        tbUsuarios.clearSelection();
        jPopupMenu1.setVisible(false);
    }

    public void eliminar() {
        int fila = tbUsuarios.getSelectedRow();
        if (fila != -1) {
            jPopupMenu1.setVisible(false);
            int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar?", "Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (resp == 0) {
                Empleado emp = new Empleado();
                int id = Integer.parseInt(tbUsuarios.getValueAt(fila, 0).toString());
                String mensaje = ebo.eliminarEmpleado(id);
                if (!mensaje.equals("ELIMINADO CORRECTAMENTE")) {
                    new rojerusan.RSNotifyFade("¡ERROR!", "¡Error al eliminar el cliente!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.WARNING).setVisible(true);
                } else {
                    new rojerusan.RSNotifyFade("¡SUCCESS!", "¡Eliminado Correctamente!", 5, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.SUCCESS).setVisible(true);
                    listar();
                }
            }
            tbUsuarios.clearSelection();
        } else {
            listar();
        }
        tbUsuarios.clearSelection();
        jPopupMenu1.setVisible(false);
    }

    public void listarF() {
        listar();
        tbUsuarios.clearSelection();
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
        btnAgregar = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnEliminarTodo = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnListar = new RSMaterialComponent.RSButtonMaterialIconOne();
        rSTextFieldIconOne1 = new RSMaterialComponent.RSTextFieldIconOne();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbUsuarios = new RSMaterialComponent.RSTableMetroCustom();

        menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEliminar.setText("ELIMINAR (F4)");
        btnEliminar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DELETE);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        menu.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, -1, -1));

        btnEditar.setText("EDITAR (F3)");
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

        jPanel2.setBackground(new java.awt.Color(102, 16, 242));

        rSLabelTextIcon1.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelTextIcon1.setText("Usuarios");
        rSLabelTextIcon1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rSLabelTextIcon1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rSLabelTextIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PERSON);

        rSLabelHora1.setBackground(new java.awt.Color(255, 255, 255));
        rSLabelHora1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rSLabelTextIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSLabelTextIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnAgregar.setText("NUEVO (F1)");
        btnAgregar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_BOX);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminarTodo.setText("ELIMINAR TODO (F2)");
        btnEliminarTodo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DELETE);
        btnEliminarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTodoActionPerformed(evt);
            }
        });

        btnListar.setText("LISTAR (F5)");
        btnListar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });

        rSTextFieldIconOne1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        rSTextFieldIconOne1.setPlaceholder("BUSCAR");

        tbUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "A. PATERNO", "A. MATERNO", "USUARIO", "CONTRASEÑA", "CORREO"
            }
        ));
        tbUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbUsuariosMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbUsuarios);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                        .addComponent(rSTextFieldIconOne1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSTextFieldIconOne1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void tbUsuariosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUsuariosMouseReleased
        if (evt.isPopupTrigger()) {
            // Obtener la fila seleccionada
            int fila = tbUsuarios.getSelectedRow();
            // Verificar si se ha seleccionado una fila
            if (fila != -1) {
                // Mostrar el JPopupMenu en la posición del clic
                jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
            } else {
                listar();
                jPopupMenu1.setVisible(false);
            }
        }
    }//GEN-LAST:event_tbUsuariosMouseReleased

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        editar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEliminarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTodoActionPerformed
        eliminarTodo();
    }//GEN-LAST:event_btnEliminarTodoActionPerformed

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        listarF();
    }//GEN-LAST:event_btnListarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonMaterialIconOne btnAgregar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnEditar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnEliminar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnEliminarTodo;
    private RSMaterialComponent.RSButtonMaterialIconOne btnListar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel menu;
    private rojeru_san.rsdate.RSLabelHora rSLabelHora1;
    private RSMaterialComponent.RSLabelTextIcon rSLabelTextIcon1;
    private RSMaterialComponent.RSTextFieldIconOne rSTextFieldIconOne1;
    private RSMaterialComponent.RSTableMetroCustom tbUsuarios;
    // End of variables declaration//GEN-END:variables
}
