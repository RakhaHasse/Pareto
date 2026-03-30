import javax.swing.*;
import java.awt.*;


public class Frame extends JFrame {

    Table table;
    TasksRepository repository;
    TasksList tasksList;

    public static void main(String[] args) {
        Frame win = new Frame();
        win.Launch();

    }



    public void Launch() {
        repository = new TasksRepository();
        tasksList = repository.loadTasks();

        setTitle("Subjective Pareto v1.7");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font font = new Font("Verdana", Font.PLAIN, 10);
        final JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(font);

        tabbedPane.add("Records", new Record(tasksList, repository));
        tabbedPane.add("Pareto Table", this.createTable());
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());



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

