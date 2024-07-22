package ajustes;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Kevscl
 */
public class EscalarImagen {

    /*
    Esta funcion sirve para escalar una imagen
     */
    public void escalar(String rutaImagen, JLabel label) {
        ImageIcon imgIcon = new ImageIcon(getClass().getResource(rutaImagen));
        Image imgEscalada = imgIcon.getImage().getScaledInstance(label.getWidth(),
                label.getHeight(), Image.SCALE_SMOOTH);
        Icon iconoEscalado = new ImageIcon(imgEscalada);
        label.setIcon(iconoEscalado);
    }

    public void escalarGif(String rutaGif, JLabel label) {
        ImageIcon gifIcon = new ImageIcon(getClass().getResource(rutaGif));
        Image gifImage = gifIcon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT);
        ImageIcon gifIconScaled = new ImageIcon(gifImage);
        label.setIcon(gifIconScaled);
    }

}
