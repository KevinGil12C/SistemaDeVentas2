package ajustes;

import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author Kevscl
 */
public class CentrarPanel {

    public void CentrarVentana(JDesktopPane ventanaPrincipal, JInternalFrame frame) {
    ventanaPrincipal.removeAll();
    ventanaPrincipal.add(frame);
    Dimension dimension = ventanaPrincipal.getSize();
    Dimension frameDimension = frame.getSize();
    int x = (dimension.width - frameDimension.width) / 2;
    int y = (dimension.height - frameDimension.height) / 2;
    frame.setBounds(x, y, frameDimension.width, frameDimension.height);
    ventanaPrincipal.revalidate();
    ventanaPrincipal.repaint();
    frame.setVisible(true);
}

}
