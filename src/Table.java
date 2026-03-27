import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;

public class Table extends JTable {
        Table (String[][] rowData,String[] names){
        super (rowData,names);
        this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);;
        this.setModel(TableModel.getModel());
        this.setDefaultEditor(Object.class, null); //Protection against table editing
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component component = super.prepareRenderer(renderer, row, column);
        int rendererWidth = component.getPreferredSize().width;
        TableColumn tableColumn = getColumnModel().getColumn(column);
        tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
        return component;
    }
}