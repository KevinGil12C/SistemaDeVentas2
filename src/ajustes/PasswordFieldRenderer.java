package ajustes;

import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Kevscl
 */
public class PasswordFieldRenderer extends DefaultTableCellRenderer {

    @Override
    protected void setValue(Object value) {
        String password = (String) value;
        StringBuilder asterisks = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            asterisks.append("*");
        }
        super.setValue(asterisks.toString());
    }
}
