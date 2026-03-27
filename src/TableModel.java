import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;


public class TableModel extends AbstractTableModel {
    private static TableModel singletone;
    private int safetyTextFieldValue = 1;

    public Integer getSafetyTextFieldValue() {
        return safetyTextFieldValue;
    }

    public void setSafetyTextFieldValue(int safetyTextFieldValue) {
        this.safetyTextFieldValue = safetyTextFieldValue;
    }

    private Double specialTaskRuleForZeroConsumption = 4.0;

    public Double getSpecialTaskRuleForZeroConsumption() {
        return specialTaskRuleForZeroConsumption;
    }

    public void setSpecialTaskRuleForZeroConsumption(Double specialTaskRuleForZeroConsumption) {
        this.specialTaskRuleForZeroConsumption = specialTaskRuleForZeroConsumption;
    }

    public static TableModel getModel(){
        if (singletone == null) {
            singletone= new TableModel();
            Task.setSpecialRuleForZeroConsumption(singletone.specialTaskRuleForZeroConsumption);
            DigitsTextField.setSafetyValue(singletone.safetyTextFieldValue);
        }
        return singletone;
    }
    private ArrayList<String[]> data;
    private String[] columnNames;

    private TableModel() {
        this.data = new ArrayList<String[]>();
        this.columnNames = new String []{
                "Task name",
                "Time",
                "Energy",
                "Consumption",
                "Moment result",
                "Prognosis",
                "Result",
                "Productivity"
        };
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
    public String [] getColumnNames () {return columnNames;}

    @Override
    public String getValueAt(int row, int column) {
        return data.get(row)[column];
    }


    public void setValueAt(String value, int row, int column) {
        data.get(row)[column] = value;
        fireTableCellUpdated(row, column);
    }

    public void addRow(String[] rowData) {
        data.add(rowData);
        int row = data.size() - 1;
        fireTableRowsInserted(row, row);
    }

    public void addRow (int index, String[] rowData){
        data.add(index,rowData);
        fireTableRowsInserted(index, index);
    }

    public void removeRow(int row) {
        data.remove(row);
        fireTableRowsDeleted(row, row);
    }



}

