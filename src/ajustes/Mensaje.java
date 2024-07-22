package ajustes;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Kevscl
 */
public class Mensaje {
    public void valido(String mensaje){
        Icon icono = new ImageIcon(getClass().getResource("valido.png"));
        // Mostrar el JOptionPane con la imagen
        JOptionPane.showMessageDialog(null, mensaje,"Mensaje", JOptionPane.PLAIN_MESSAGE, icono);
    }
    public void noValido(String mensaje){
        Icon icono = new ImageIcon(getClass().getResource("error.png"));
        // Mostrar el JOptionPane con la imagen
        JOptionPane.showMessageDialog(null, mensaje,"Mensaje", JOptionPane.PLAIN_MESSAGE, icono);
    }
}
