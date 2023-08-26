import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;

public class TableModel extends AbstractTableModel {
    private ArrayList<Object[]> data;
    private String[] columnNames;

    public TableModel(ArrayList<Object[]> data, String[] columnNames) {
        this.data = data;
        this.columnNames = columnNames;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int row, int column) {
        return data.get(row)[column];
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        data.get(row)[column] = value;
        fireTableCellUpdated(row, column);
    }

    public void addRow(Object[] rowData) {
        data.add(rowData);
        int row = data.size() - 1;
        fireTableRowsInserted(row, row);
    }

    public void removeRow(int row) {
        data.remove(row-1);
        fireTableRowsDeleted(row, row);
    }

    public void removeRow (String[] sArray){
        for (int i = 0; i<this.data.size();i++){
                removeRow(i);

            System.out.println("Row deleted");
        }

    }
}

