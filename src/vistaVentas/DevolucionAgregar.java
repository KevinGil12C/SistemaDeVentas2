/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistaVentas;

import bo.DevolucionBO;
import entity.Devolucion;
import java.text.SimpleDateFormat;
import java.util.Date;
import rojerusan.RSNotifyFade;

/**
 *
 * @author Kevscl
 */
public class DevolucionAgregar extends javax.swing.JFrame {

    /**
     * Creates new form DevolucionAgregar
     */
    DevolucionBO dbo = new DevolucionBO();
    Devolucion d = new Devolucion();

    public DevolucionAgregar() {
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        idMax();
    }

    public void devolver() {
        int idDevo = Integer.parseInt(labelIDDevo.getText());
        int idVent = Integer.parseInt(txtIdVenta.getText());
        String pro = txtProducto.getText().trim(); // Elimina espacios en blanco al inicio y final
        int cantid = Integer.parseInt(txtPzDevo.getText());
        Date fechaSeleccionada = dateFecha.getDate();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        String fechaFormateada = formato.format(fechaSeleccionada);
        String motivo = txtMotivo.getText().trim(); // Elimina espacios en blanco al inicio y final

        // Validar que los campos no sean nulos o vacíos
        if (idDevo != 0 && idVent != 0 && !pro.isEmpty() && cantid > 0 && fechaSeleccionada != null && !motivo.isEmpty()) {
            // Los datos son válidos, continúa con el proceso
            d = new Devolucion();
            d.setIdDevolucion(idDevo);
            d.setIdVenta(idVent);
            d.setCantidadDevuelta(cantid);
            d.setFechaDevuelta(fechaFormateada);
            d.setMotivo(motivo);
            String mensaje = dbo.agregarDevolucion(d, pro);
            if (!mensaje.equals("DEVOLUCIÓN REGISTRADA CORRECTAMENTE")) {
                new rojerusan.RSNotifyFade("¡ERROR!", "¡Error al registrar la devolución!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.WARNING).setVisible(true);
            } else {
                new rojerusan.RSNotifyFade("¡SUCCESS!", "¡Devolución Registrada Correctamente!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.SUCCESS).setVisible(true);
                dispose();
            }
            idMax();
        } else {
            new rojerusan.RSNotifyFade("¡WARNING!", "¡Llene todos los campos correctamente!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.ERROR).setVisible(true);
        }
    }

    public void idMax() {
        labelIDDevo.setText(dbo.getMaxID());
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
        jLabel1 = new javax.swing.JLabel();
        rSLabelIcon3 = new RSMaterialComponent.RSLabelIcon();
        rSPanelGradiente1 = new rojeru_san.rspanel.RSPanelGradiente();
        rSPanelImage1 = new rojeru_san.rspanel.RSPanelImage();
        txtProducto = new RSMaterialComponent.RSTextFieldMaterialIcon();
        txtCantidad = new RSMaterialComponent.RSTextFieldMaterialIcon();
        btnRegistrar = new rojeru_san.rsbutton.RSButtonGradiente();
        labelIDDevo = new javax.swing.JLabel();
        txtMotivo = new RSMaterialComponent.RSTextFieldMaterialIcon();
        dateFecha = new newscomponents.RSDateChooserModern();
        txtPzDevo = new RSMaterialComponent.RSTextFieldMaterialIcon();
        labelpz = new javax.swing.JLabel();
        labelidventa = new javax.swing.JLabel();
        txtIdVenta = new RSMaterialComponent.RSTextFieldMaterialIcon();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(58, 159, 171));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("REGISTRAR DEVOLUCIÓN");

        rSLabelIcon3.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon3.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLOSE);
        rSLabelIcon3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSLabelIcon3MouseClicked(evt);
            }
        });

        rSPanelGradiente1.setColorPrimario(new java.awt.Color(255, 255, 255));
        rSPanelGradiente1.setColorSecundario(new java.awt.Color(58, 159, 171));

        rSPanelImage1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/imgProducto/caja.png"))); // NOI18N

        javax.swing.GroupLayout rSPanelImage1Layout = new javax.swing.GroupLayout(rSPanelImage1);
        rSPanelImage1.setLayout(rSPanelImage1Layout);
        rSPanelImage1Layout.setHorizontalGroup(
            rSPanelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        rSPanelImage1Layout.setVerticalGroup(
            rSPanelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        txtProducto.setForeground(new java.awt.Color(58, 159, 171));
        txtProducto.setColorIcon(new java.awt.Color(58, 159, 171));
        txtProducto.setColorMaterial(new java.awt.Color(58, 159, 171));
        txtProducto.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.LIBRARY_ADD);
        txtProducto.setPlaceholder("Producto");

        txtCantidad.setForeground(new java.awt.Color(58, 159, 171));
        txtCantidad.setColorIcon(new java.awt.Color(58, 159, 171));
        txtCantidad.setColorMaterial(new java.awt.Color(58, 159, 171));
        txtCantidad.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);
        txtCantidad.setPlaceholder("Precio");

        btnRegistrar.setText("AGREGAR");
        btnRegistrar.setColorPrimario(new java.awt.Color(58, 159, 171));
        btnRegistrar.setColorPrimarioHover(new java.awt.Color(38, 159, 171));
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        labelIDDevo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelIDDevo.setText("1");

        txtMotivo.setForeground(new java.awt.Color(58, 159, 171));
        txtMotivo.setColorIcon(new java.awt.Color(58, 159, 171));
        txtMotivo.setColorMaterial(new java.awt.Color(58, 159, 171));
        txtMotivo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ERROR);
        txtMotivo.setPlaceholder("Motivo");

        dateFecha.setBackground(new java.awt.Color(58, 159, 171));

        txtPzDevo.setForeground(new java.awt.Color(58, 159, 171));
        txtPzDevo.setColorIcon(new java.awt.Color(58, 159, 171));
        txtPzDevo.setColorMaterial(new java.awt.Color(58, 159, 171));
        txtPzDevo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MORE);
        txtPzDevo.setInheritsPopupMenu(true);
        txtPzDevo.setPlaceholder("Pz Devolución");

        labelpz.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelpz.setText("Pz:");

        labelidventa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelidventa.setText("Id Venta:");

        txtIdVenta.setForeground(new java.awt.Color(58, 159, 171));
        txtIdVenta.setColorIcon(new java.awt.Color(58, 159, 171));
        txtIdVenta.setColorMaterial(new java.awt.Color(58, 159, 171));
        txtIdVenta.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MORE_HORIZ);
        txtIdVenta.setPlaceholder("Id Venta");

        javax.swing.GroupLayout rSPanelGradiente1Layout = new javax.swing.GroupLayout(rSPanelGradiente1);
        rSPanelGradiente1.setLayout(rSPanelGradiente1Layout);
        rSPanelGradiente1Layout.setHorizontalGroup(
            rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelIDDevo)
                .addGap(29, 29, 29))
            .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelGradiente1Layout.createSequentialGroup()
                                .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtMotivo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                                        .addComponent(labelpz)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtPzDevo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                                .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                                        .addComponent(dateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(178, 178, 178)
                                        .addComponent(labelidventa)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                        .addGap(359, 359, 359)
                        .addComponent(rSPanelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        rSPanelGradiente1Layout.setVerticalGroup(
            rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelGradiente1Layout.createSequentialGroup()
                .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelIDDevo))
                    .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(rSPanelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                                .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelpz))
                                .addGap(18, 18, 18)
                                .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPzDevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(dateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelidventa)
                                .addComponent(txtIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                        .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSLabelIcon3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(rSPanelGradiente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(rSLabelIcon3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSPanelGradiente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSLabelIcon3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSLabelIcon3MouseClicked
        dispose();
    }//GEN-LAST:event_rSLabelIcon3MouseClicked

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        devolver();
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
            java.util.logging.Logger.getLogger(DevolucionAgregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DevolucionAgregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DevolucionAgregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DevolucionAgregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DevolucionAgregar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.rsbutton.RSButtonGradiente btnRegistrar;
    private newscomponents.RSDateChooserModern dateFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JLabel labelIDDevo;
    private javax.swing.JLabel labelidventa;
    private javax.swing.JLabel labelpz;
    private RSMaterialComponent.RSLabelIcon rSLabelIcon3;
    private rojeru_san.rspanel.RSPanelGradiente rSPanelGradiente1;
    private rojeru_san.rspanel.RSPanelImage rSPanelImage1;
    public static RSMaterialComponent.RSTextFieldMaterialIcon txtCantidad;
    public static RSMaterialComponent.RSTextFieldMaterialIcon txtIdVenta;
    public static RSMaterialComponent.RSTextFieldMaterialIcon txtMotivo;
    public static RSMaterialComponent.RSTextFieldMaterialIcon txtProducto;
    public static RSMaterialComponent.RSTextFieldMaterialIcon txtPzDevo;
    // End of variables declaration//GEN-END:variables
}