package vistaUsuario;

import ajustes.PanelListener;
import bo.EmpleadoBO;
import entity.Empleado;
import rojerusan.RSNotifyFade;

/**
 *
 * @author Kevscl
 */
public class agregarUsuario extends javax.swing.JFrame {

    EmpleadoBO ebo = new EmpleadoBO();
    Empleado emp = new Empleado();

    public agregarUsuario() {
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        idMax();
    }

    private PanelListener panelListener;

    public void setPanelListener(PanelListener listener) {
        this.panelListener = listener;
    }

    public void idMax() {
        labelID.setText(ebo.getMaxID());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSPanelGradiente1 = new rojeru_san.rspanel.RSPanelGradiente();
        rSLabelImage1 = new necesario.RSLabelImage();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rSLabelIcon3 = new RSMaterialComponent.RSLabelIcon();
        txtNombre = new RSMaterialComponent.RSTextFieldMaterialIcon();
        txtAP = new RSMaterialComponent.RSTextFieldMaterialIcon();
        txtAM = new RSMaterialComponent.RSTextFieldMaterialIcon();
        txtUsuario = new RSMaterialComponent.RSTextFieldMaterialIcon();
        txtPass = new RSMaterialComponent.RSPasswordMaterialIcon();
        btnRegistrar = new newscomponents.RSButtonGradientIcon_new();
        labelID = new javax.swing.JLabel();
        txtCorreo = new RSMaterialComponent.RSTextFieldMaterialIcon();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSPanelGradiente1.setColorPrimario(new java.awt.Color(102, 16, 242));
        rSPanelGradiente1.setColorSecundario(new java.awt.Color(204, 204, 204));

        rSLabelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgEmpleado/empleado.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(102, 16, 242));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("REGISTRAR USUARIO");

        rSLabelIcon3.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon3.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLOSE);
        rSLabelIcon3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSLabelIcon3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(rSLabelIcon3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSLabelIcon3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtNombre.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ACCOUNT_BOX);
        txtNombre.setPlaceholder("Nombre");

        txtAP.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ACCOUNT_CIRCLE);
        txtAP.setPlaceholder("Apellido Paterno");

        txtAM.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ACCOUNT_CIRCLE);
        txtAM.setPlaceholder("Apellido Materno");

        txtUsuario.setPlaceholder("Usuario");

        txtPass.setPlaceholder("Password");

        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.setColorPrimario(new java.awt.Color(102, 16, 242));
        btnRegistrar.setColorSecundario(new java.awt.Color(153, 0, 255));
        btnRegistrar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_CIRCLE_OUTLINE);
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        labelID.setText("1");

        txtCorreo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EMAIL);
        txtCorreo.setPlaceholder("Correo");

        javax.swing.GroupLayout rSPanelGradiente1Layout = new javax.swing.GroupLayout(rSPanelGradiente1);
        rSPanelGradiente1.setLayout(rSPanelGradiente1Layout);
        rSPanelGradiente1Layout.setHorizontalGroup(
            rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(rSLabelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                            .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtAM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtAP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(labelID)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        rSPanelGradiente1Layout.setVerticalGroup(
            rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSLabelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelID))
                .addGap(17, 17, 17))
        );

        getContentPane().add(rSPanelGradiente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSLabelIcon3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSLabelIcon3MouseClicked
        dispose();
    }//GEN-LAST:event_rSLabelIcon3MouseClicked

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased

    }//GEN-LAST:event_formMouseReleased

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged

    }//GEN-LAST:event_formMouseDragged

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        int id = Integer.parseInt(labelID.getText());
        String nom = txtNombre.getText();
        String ape1 = txtAP.getText();
        String ape2 = txtAM.getText();
        String user = txtUsuario.getText();
        String pass = txtPass.getText();
        String email = txtCorreo.getText();
        if (id != 0 && nom != null && !nom.isEmpty() && ape1 != null && !ape1.isEmpty()
                && ape2 != null && !ape2.isEmpty() && user != null && !user.isEmpty()
                && pass != null && !pass.isEmpty()
                && email != null && !email.isEmpty()) {
            // Los datos son válidos, continúa con el proceso
            emp = new Empleado();
            emp.setIdEmpleado(id);
            emp.setNombre(nom);
            emp.setApe1Usuario(ape1);
            emp.setApe2Usuario(ape2);
            emp.setUsuario(user);
            emp.setClave(pass);
            emp.setCorreo(email);
            String mensaje = ebo.agregarEmpleado(emp);

            if (!mensaje.equals("GUARDADO CORRECTAMENTE")) {
                new rojerusan.RSNotifyFade("¡ERROR!", "¡Error al guardar al empleado!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.WARNING).setVisible(true);
            } else {
                new rojerusan.RSNotifyFade("¡SUCCESS!", "¡Guardado Correctamente!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.SUCCESS).setVisible(true);
                if (panelListener != null) {
                    panelListener.onPanelClosed();
                }
                dispose();
            }
            idMax();
        } else {
            new rojerusan.RSNotifyFade("¡WARNING!", "¡Llene todos los campos!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.ERROR).setVisible(true);
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(agregarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(agregarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(agregarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(agregarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new agregarUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private newscomponents.RSButtonGradientIcon_new btnRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelID;
    private RSMaterialComponent.RSLabelIcon rSLabelIcon3;
    private necesario.RSLabelImage rSLabelImage1;
    private rojeru_san.rspanel.RSPanelGradiente rSPanelGradiente1;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtAM;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtAP;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtCorreo;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtNombre;
    private RSMaterialComponent.RSPasswordMaterialIcon txtPass;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtUsuario;
    // End of variables declaration//GEN-END:variables
}
