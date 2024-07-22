/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistaLogin;

import ajustes.EmailSender;
import bo.EmpleadoBO;
import entity.Empleado;
import java.awt.Point;
import rojerusan.RSNotifyFade;

/**
 *
 * @author Kevscl
 */
public class RecuperaInicio extends javax.swing.JFrame {

    private Point mPoint;
    EmailSender env = new EmailSender();
    Empleado emp = new Empleado();
    EmpleadoBO ebo = new EmpleadoBO();
    /**
     * Creates new form RecuperaInicio
     */
    public RecuperaInicio() {
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
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
        rSPanelShadow1 = new RSMaterialComponent.RSPanelShadow();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnRecuperar = new rojeru_san.rsbutton.RSButtonGradiente();
        txtNombre = new RSMaterialComponent.RSTextFieldMaterialIcon();
        txtAP = new RSMaterialComponent.RSTextFieldMaterialIcon();
        txtAM = new RSMaterialComponent.RSTextFieldMaterialIcon();
        txtCorreo = new RSMaterialComponent.RSTextFieldMaterialIcon();
        txtUsuario = new RSMaterialComponent.RSTextFieldMaterialIcon();
        rSPanelMaterialImage1 = new RSMaterialComponent.RSPanelMaterialImage();
        btnSalir = new rojeru_san.RSButtonRiple();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        rSPanelGradiente1.setColorPrimario(new java.awt.Color(255, 153, 255));
        rSPanelGradiente1.setColorSecundario(new java.awt.Color(220, 76, 100));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(220, 76, 100));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("RECUPERA TU CUENTA");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(220, 76, 100));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("INGRESA TUS DATOS");

        btnRecuperar.setText("RECUPERAR");
        btnRecuperar.setColorPrimario(new java.awt.Color(37, 45, 223));
        btnRecuperar.setColorPrimarioHover(new java.awt.Color(153, 51, 204));
        btnRecuperar.setColorSecundario(new java.awt.Color(153, 51, 204));
        btnRecuperar.setColorSecundarioHover(new java.awt.Color(37, 45, 223));
        btnRecuperar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecuperarActionPerformed(evt);
            }
        });

        txtNombre.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ACCOUNT_BOX);
        txtNombre.setPlaceholder("Nombre");

        txtAP.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ACCOUNT_CIRCLE);
        txtAP.setPlaceholder("Apellido Paterno");

        txtAM.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ACCOUNT_CIRCLE);
        txtAM.setPlaceholder("Apellido Materno");

        txtCorreo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EMAIL);
        txtCorreo.setPlaceholder("Correo");

        txtUsuario.setPlaceholder("Usuario");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnRecuperar, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtAM, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtAP, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(12, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 292, Short.MAX_VALUE)
                .addComponent(btnRecuperar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(74, 74, 74)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(txtAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(txtAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(75, Short.MAX_VALUE)))
        );

        rSPanelShadow1.add(jPanel1, java.awt.BorderLayout.CENTER);

        rSPanelMaterialImage1.setBackground(new java.awt.Color(255, 255, 255));
        rSPanelMaterialImage1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/imgPrincipal/logo-black.png"))); // NOI18N
        rSPanelMaterialImage1.setShadowBottom(false);
        rSPanelMaterialImage1.setShadowLeft(false);
        rSPanelMaterialImage1.setShadowRight(false);
        rSPanelMaterialImage1.setShadowTop(false);

        javax.swing.GroupLayout rSPanelMaterialImage1Layout = new javax.swing.GroupLayout(rSPanelMaterialImage1);
        rSPanelMaterialImage1.setLayout(rSPanelMaterialImage1Layout);
        rSPanelMaterialImage1Layout.setHorizontalGroup(
            rSPanelMaterialImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 344, Short.MAX_VALUE)
        );
        rSPanelMaterialImage1Layout.setVerticalGroup(
            rSPanelMaterialImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
        );

        btnSalir.setBackground(new java.awt.Color(220, 76, 100));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgLogin/btn-cerrar.png"))); // NOI18N
        btnSalir.setColorHover(new java.awt.Color(69, 87, 252));
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rSPanelGradiente1Layout = new javax.swing.GroupLayout(rSPanelGradiente1);
        rSPanelGradiente1.setLayout(rSPanelGradiente1Layout);
        rSPanelGradiente1Layout.setHorizontalGroup(
            rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(rSPanelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                        .addGap(404, 404, 404)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(rSPanelMaterialImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        rSPanelGradiente1Layout.setVerticalGroup(
            rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(rSPanelMaterialImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(rSPanelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSPanelGradiente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSPanelGradiente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRecuperarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecuperarActionPerformed
        String nom = txtNombre.getText();
        String ape1 = txtAP.getText();
        String ape2 = txtAM.getText();
        String user = txtUsuario.getText();
        String email = txtCorreo.getText();
        if (nom != null && !nom.isEmpty() && ape1 != null && !ape1.isEmpty()
                && ape2 != null && !ape2.isEmpty() && user != null && !user.isEmpty()
                && email != null && !email.isEmpty()) {
            // Los datos son válidos, continúa con el proceso
            emp = new Empleado();
            emp.setNombre(nom);
            emp.setApe1Usuario(ape1);
            emp.setApe2Usuario(ape2);
            emp.setUsuario(user);
            emp.setCorreo(email);
            String mensaje = ebo.recuperarContraseña(nom, ape1, ape2, email,user);
            if (mensaje.equals("ERROR")) {
                new rojerusan.RSNotifyFade("¡ERROR!", "¡Error al buscar al empleado!", 3, RSNotifyFade.PositionNotify.TopLef.TopRight, RSNotifyFade.TypeNotify.WARNING).setVisible(true);            
            } else {
                String nombre = nom+" "+ape1+" "+ape2;
                env.recuperaClave(nombre, email, user, mensaje);
                new rojerusan.RSNotifyFade("¡SUCCESS!", "¡Correo enviado exitosamente.!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.SUCCESS).setVisible(true);
                dispose();
            }
        } else {
            new rojerusan.RSNotifyFade("¡WARNING!", "¡Llene todos los campos!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.ERROR).setVisible(true);
        }        
    }//GEN-LAST:event_btnRecuperarActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        mPoint = evt.getPoint();
        getComponentAt(mPoint);
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        int CurrentX = this.getLocation().x;
        int CurrentY = this.getLocation().y;

        int MoveX = (CurrentX + evt.getX()) - (CurrentX + mPoint.x);
        int MoveY = (CurrentY + evt.getY()) - (CurrentY + mPoint.y);

        int x = CurrentX + MoveX;
        int y = CurrentY + MoveY;

        this.setLocation(x, y);
    }//GEN-LAST:event_formMouseDragged

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

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
            java.util.logging.Logger.getLogger(RecuperaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RecuperaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RecuperaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RecuperaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RecuperaInicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.rsbutton.RSButtonGradiente btnRecuperar;
    private rojeru_san.RSButtonRiple btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private rojeru_san.rspanel.RSPanelGradiente rSPanelGradiente1;
    private RSMaterialComponent.RSPanelMaterialImage rSPanelMaterialImage1;
    private RSMaterialComponent.RSPanelShadow rSPanelShadow1;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtAM;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtAP;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtCorreo;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtNombre;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtUsuario;
    // End of variables declaration//GEN-END:variables
}