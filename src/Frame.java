import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;


public class Frame extends JFrame
{
    JTextField energy, time, now, prognosis, name;
    JTable table;
    JComboBox optionsList;
    TasksList Tasks;

    private TableModel model;
    public void Launch (){
        Tasks = new TasksList();

        setTitle("Subjective Pareto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font font = new Font("Verdana", Font.PLAIN, 10);
        final JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(font);
        tabbedPane.add("Records", createRecord());

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());

        time.setDocument(getDocument());
        energy.setDocument(getDocument());
        now.setDocument(getDocument());
        prognosis.setDocument(getDocument());


        tabbedPane.add("Pareto Table", this.createTable());

        content.add(tabbedPane, BorderLayout.CENTER);
        getContentPane().add(content);

        pack();
        setLocationRelativeTo(null);

        setVisible(true);
    }

    private JPanel createTable(){
        model = new TableModel(new ArrayList<Object[]>(), Tasks.columnNames);
        JTable table = new JTable(new String[][]{Tasks.columnNames}, Tasks.columnNames){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                int rendererWidth = component.getPreferredSize().width;
                TableColumn tableColumn = getColumnModel().getColumn(column);
                tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
                return component;
            }
        };
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);;
        table.setModel(model);
        JPanel Table = new JPanel();
        JScrollPane scrollPane = new JScrollPane(table);
        Table.add(table.getTableHeader(),BorderLayout.NORTH);
        Table.add( table, BorderLayout.CENTER);
        Table.add(scrollPane);
        Table.setPreferredSize(new Dimension(1000, 420));
        this.table=table;



        //Protection against table editing
        table.setDefaultEditor(Object.class, null);

        return Table;
    }
    private JPanel createRecord (){
        JPanel result = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel nameD = new JLabel( "Task name");
        constraints.gridx =0;
        constraints.gridy =0;
        result.add(nameD,constraints);
        JTextField name = new JTextField("Enter task name", 10);
        this.name = name;
        constraints.gridx = 1;
        result.add(name,constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        JLabel timeD = new JLabel("Time consumption:");
        result.add(timeD, constraints);
        constraints.gridx = 1;
        JTextField time = new JTextField("Enter value ", 10);

        this.time = time;
        result.add(time, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        JLabel energyD = new JLabel("Energy consumption");
        result.add(energyD, constraints);
        constraints.gridx = 1;
        JTextField energy = new JTextField("Enter value:", 10);
        this.energy = energy;
        result.add(energy, constraints);

        constraints.gridy = 3;
        constraints.gridx = 0;
        JLabel nowD = new JLabel("Result now:");
        result.add(nowD, constraints);
        constraints.gridx = 1;
        JTextField now = new JTextField("Enter value", 10);
        this.now = now;
        result.add(now, constraints);

        constraints.gridy = 4;
        constraints.gridx = 0;
        JLabel prognosisD = new JLabel("Prognosis result:");
        result.add(prognosisD, constraints);
        constraints.gridx = 1;
        JTextField prognosis = new JTextField("Enter value", 10);
        this.prognosis = prognosis;
        result.add(prognosis, constraints);

        constraints.gridy = 5;
        constraints.gridx = 0;
        constraints.gridwidth =2;
        String[] optionsToChoose = {"New task"};
        JComboBox<String> list = new JComboBox<>(optionsToChoose);
        list.setBounds(80, 50, 140, 20);
        result.add(list, constraints);
        optionsList=list;

        JButton addTask = new JButton("Add task");
        constraints.gridy = 6;
        constraints.gridx=0;
        constraints.gridwidth=1;
        result.add(addTask,constraints);
        addTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionAddButton(e);
            }
        });

        JButton changeTask = new JButton("Change task");
        constraints.gridy = 6;
        constraints.gridx=1;
        constraints.gridwidth=1;
        result.add(changeTask,constraints);
        changeTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionChangeButton(e);
            }
        });

        JButton deleteTask = new JButton("Delete task");
        constraints.gridx = 2;
        result.add(deleteTask,constraints);
        deleteTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionDeleteTask();
            }
        });

        return result;
    }

    public void actionAddButton (ActionEvent e){
        if (optionsList.getSelectedItem().equals("New task")) {
            String n = this.name.getText();
            int t = Integer.parseInt(this.time.getText());
            int en = Integer.parseInt(this.energy.getText());
            int no = Integer.parseInt(this.now.getText());
            int p = Integer.parseInt(this.prognosis.getText());
            Task task = null;
            if (t != 0) if (en != 0) if (no != 0) if (p != 0) task = new Task(n, en, t, no, p);
            else task = new Task(n);

            Tasks.add(task);
            optionsList.addItem(task.getName());
            model.addRow(task.toStringArray());
        }

    }

    public void actionChangeButton (ActionEvent e){
        if (!optionsList.getSelectedItem().equals("New task")) {

            String n = this.name.getText();
            int t = Integer.parseInt(this.time.getText());
            int en = Integer.parseInt(this.energy.getText());
            int no = Integer.parseInt(this.now.getText());
            int p = Integer.parseInt(this.prognosis.getText());
            Task temp = Tasks.getTask(Objects.requireNonNull(optionsList.getSelectedItem()).toString());
            model.removeRow(optionsList.getSelectedIndex());
            temp.setName(n);
            temp.setNowResult(no);
            temp.setEnergyConsumption(en);
            temp.setPrognosisResult(p);
            temp.setTimeConsumption(t);
            model.addRow(temp.toStringArray());
            model.fireTableRowsUpdated(1,model.getRowCount());
            optionsList.removeItem(optionsList.getSelectedItem());
            optionsList.addItem(n);




        }
    }

    public PlainDocument getDocument () {
        PlainDocument doc0 = new PlainDocument();
        doc0.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int off, String str, AttributeSet attr)
                    throws BadLocationException {
                fb.insertString(off, str.replaceAll("\\D++", ""), attr);  // remove non-digits
            }

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int off, int len, String str, AttributeSet attr)
                    throws BadLocationException {
                fb.replace(off, len, str.replaceAll("\\D++", ""), attr);  // remove non-digits
            }
        });
        return doc0;
    }

    public void actionDeleteTask (){

        if(!optionsList.getSelectedItem().toString().equals("New task")) {
            Tasks.remove(Tasks.getTask(optionsList.getSelectedItem().toString()));
            model.removeRow(optionsList.getSelectedIndex());
            optionsList.removeItem(optionsList.getSelectedItem());
        }
    }
}
