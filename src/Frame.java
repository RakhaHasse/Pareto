import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class Frame extends JFrame {

    Table table;

    TasksList Tasks;

    public static void main(String[] args) {
        Frame win = new Frame();
        win.Launch();

    }

    public void Launch() {
        Tasks = new TasksList();

        setTitle("Subjective Pareto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font font = new Font("Verdana", Font.PLAIN, 10);
        final JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(font);
        tabbedPane.add("Records", new Record(Tasks));

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());

        tabbedPane.add("Pareto Table", this.createTable());

        content.add(tabbedPane, BorderLayout.CENTER);
        getContentPane().add(content);

        pack();
        setLocationRelativeTo(null);

        setVisible(true);
    }

    private JPanel createTable() {

        this.table = new Table(new String[][]{TableModel.getModel().getColumnNames()},
                TableModel.getModel().getColumnNames()) {
        };

        JPanel Table = new JPanel();
        JScrollPane scrollPane = new JScrollPane(table);
        Table.add(table.getTableHeader(), BorderLayout.NORTH);
        Table.add(table, BorderLayout.CENTER);
        Table.add(scrollPane);
        Table.setPreferredSize(new Dimension(1000, 420));

        return Table;
    }


}

