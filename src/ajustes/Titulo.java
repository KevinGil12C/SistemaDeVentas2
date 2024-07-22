package ajustes;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import javax.swing.JLabel;

/**
 *
 * @author Kevscl
 */
public class Titulo {

    public void titulo2d(JLabel label, String texto) {
        Font font = new Font("Arial", Font.BOLD, 24);
        GradientPaint gradient = new GradientPaint(0, 0, Color.BLUE, 200, 0, Color.RED);;
        FontRenderContext frc = new FontRenderContext(null, true, true);
        TextLayout layout = new TextLayout(texto, font, frc);

        // Crear una clase anónima que extienda JLabel y sobrescriba el método paintComponent
        label = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setPaint(gradient);
                g2.setFont(font);
                layout.draw(g2, 0, 30);
            }
        };
    }
}
