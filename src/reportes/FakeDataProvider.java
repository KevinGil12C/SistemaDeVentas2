package reportes;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

public class FakeDataProvider {

    // ... (Rest of your FakeDataProvider code)

    // Modified method to accept JTable and return List of headers
    public static List<String> getTableHeaders(JTable table) {
        List<String> tableHeader = new ArrayList<>();
        for (int i = 0; i < table.getColumnCount(); i++) {
            tableHeader.add(table.getColumnName(i));
        }
        return tableHeader;
    }

    // Modified method to accept JTable and return List of rows
    public static List<List<String>> getTableContent(JTable table, int numberOfRows) {
        // Your implementation to fetch data from the JTable and return the content in List of Lists
        List<List<String>> tableContent = new ArrayList<>();
        int rowCount = Math.min(numberOfRows, table.getRowCount());
        for (int i = 0; i < rowCount; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < table.getColumnCount(); j++) {
                row.add(table.getValueAt(i, j).toString());
            }
            tableContent.add(row);
        }
        return tableContent;
    }
}


