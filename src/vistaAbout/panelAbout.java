package vistaAbout;

import ajustes.*;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevscl
 */
public class panelAbout extends javax.swing.JPanel {
    EscalarImagen e = new EscalarImagen();
    /**
     * Creates new form panelAbout
     */
    public panelAbout() {
        initComponents(); 
    }
    public void escalar(){
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rSLabelLineWrap1 = new rojeru_san.rslabel.RSLabelLineWrap();
        rSPanelMaterialImage1 = new RSMaterialComponent.RSPanelMaterialImage();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnFace = new rojeru_san.rsbutton.RSButtonRoundEffectIcon();
        bntGit = new rojeru_san.rsbutton.RSButtonRoundEffectIcon();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rSPanelMaterialImage2 = new RSMaterialComponent.RSPanelMaterialImage();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("ACERCA DE");

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Aviso de privacidad");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        rSLabelLineWrap1.setColorForeground(new java.awt.Color(0, 0, 0));
        rSLabelLineWrap1.setFuente(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        rSLabelLineWrap1.setText("El presente Aviso de Privacidad establece los términos y condiciones bajo los cuales \"Ropa Leo Uniformes Escolares\", utiliza y protege la información que es proporcionada por los usuarios al utilizar el sistema de ventas \"Sistema de Ventas Leo\", desarrollado por Kevscl.\n\nLa Empresa se compromete a proteger la información personal de los usuarios y a implementar medidas de seguridad adecuadas para prevenir el acceso no autorizado, la divulgación, modificación o destrucción de los datos recopilados. Se utilizarán métodos de encriptación y se mantendrán salvaguardias físicas y electrónicas para garantizar la seguridad de la información.\n\nLa Empresa no venderá, cederá ni transferirá la información personal de los usuarios a terceros, a menos que sea necesario para cumplir con los fines mencionados anteriormente o cuando la ley lo requiera.\n\nLos usuarios podrán ejercer los derechos de acceso, rectificación, cancelación y oposición (derechos ARCO) sobre sus datos personales. Para ejercer estos derechos, deberán enviar una solicitud por escrito al siguiente correo electrónico: kebo.jcg77@gmail.com\n\n");
        jPanel1.add(rSLabelLineWrap1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 620, 248));

        rSPanelMaterialImage1.setBackground(new java.awt.Color(255, 255, 255));
        rSPanelMaterialImage1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/imgPrincipal/logo-black.png"))); // NOI18N
        rSPanelMaterialImage1.setShadowBottom(false);
        rSPanelMaterialImage1.setShadowLeft(false);
        rSPanelMaterialImage1.setShadowRight(false);
        rSPanelMaterialImage1.setShadowTop(false);
        jPanel1.add(rSPanelMaterialImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 310, 220));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Soporte y Desarrollo");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        btnFace.setBackground(new java.awt.Color(69, 87, 252));
        btnFace.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgAbout/btn-facebook.png"))); // NOI18N
        btnFace.setText("Kevin Heath");
        btnFace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFaceActionPerformed(evt);
            }
        });
        jPanel2.add(btnFace, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, 221, -1));

        bntGit.setBackground(new java.awt.Color(69, 87, 252));
        bntGit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgAbout/btn-github.png"))); // NOI18N
        bntGit.setText("Kevin Gil");
        bntGit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntGitActionPerformed(evt);
            }
        });
        jPanel2.add(bntGit, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 221, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(93, 97, 92));
        jLabel3.setText("Tel: (+52) 722-159-5250");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, 138, 34));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(93, 97, 92));
        jLabel4.setText("Correo: kebo.jcg77@gmail.com");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 192, 34));

        rSPanelMaterialImage2.setImagen(new javax.swing.ImageIcon(getClass().getResource("/imgPrincipal/logo-mio-black.png"))); // NOI18N
        rSPanelMaterialImage2.setShadowBottom(false);
        rSPanelMaterialImage2.setShadowLeft(false);
        rSPanelMaterialImage2.setShadowRight(false);
        rSPanelMaterialImage2.setShadowTop(false);
        jPanel2.add(rSPanelMaterialImage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 230, 160));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel5))
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 990, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 990, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel5)
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFaceActionPerformed
        try {
            Desktop.getDesktop().browse(new URI("https://www.facebook.com/kevinHeath120"));
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(panelAbout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnFaceActionPerformed

    private void bntGitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntGitActionPerformed
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/KevinGil12C"));
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(panelAbout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bntGitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.rsbutton.RSButtonRoundEffectIcon bntGit;
    private rojeru_san.rsbutton.RSButtonRoundEffectIcon btnFace;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private rojeru_san.rslabel.RSLabelLineWrap rSLabelLineWrap1;
    private RSMaterialComponent.RSPanelMaterialImage rSPanelMaterialImage1;
    private RSMaterialComponent.RSPanelMaterialImage rSPanelMaterialImage2;
    // End of variables declaration//GEN-END:variables
}