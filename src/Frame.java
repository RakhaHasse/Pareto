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
    TextField energy, time, now, prognosis, name;
    Table table;
    JComboBox optionsList;
    TasksList Tasks;

    public static void main(String[] args) {
        Frame win = new Frame();
        win.Launch();

    }

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

        Table table = new Table(new String[][]{TableModel.getModel().getColumnNames()},
                TableModel.getModel().getColumnNames()){
        };

        JPanel Table = new JPanel();
        JScrollPane scrollPane = new JScrollPane(table);
        Table.add(table.getTableHeader(),BorderLayout.NORTH);
        Table.add( table, BorderLayout.CENTER);
        Table.add(scrollPane);
        Table.setPreferredSize(new Dimension(1000, 420));
        this.table=table;

        this.table.setDefaultEditor(Object.class, null); //Protection against table editing

        return Table;
    }
    private JPanel createRecord (){
        JPanel result = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel nameD = new JLabel( "Task name");
        constraints.gridx =0;
        constraints.gridy =0;
        result.add(nameD,constraints);
        TextField name = new TextField("Enter task name", 10);
        this.name = name;
        constraints.gridx = 1;
        result.add(name,constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        JLabel timeD = new JLabel("Time consumption:");
        result.add(timeD, constraints);
        constraints.gridx = 1;
        TextField time = new TextField("Enter value ", 10);

        this.time = time;
        result.add(time, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        JLabel energyD = new JLabel("Energy consumption");
        result.add(energyD, constraints);
        constraints.gridx = 1;
        TextField energy = new TextField("Enter value:", 10);
        this.energy = energy;
        result.add(energy, constraints);

        constraints.gridy = 3;
        constraints.gridx = 0;
        JLabel nowD = new JLabel("Result now:");
        result.add(nowD, constraints);
        constraints.gridx = 1;
        TextField now = new TextField("Enter value", 10);
        this.now = now;
        result.add(now, constraints);

        constraints.gridy = 4;
        constraints.gridx = 0;
        JLabel prognosisD = new JLabel("Prognosis result:");
        result.add(prognosisD, constraints);
        constraints.gridx = 1;
        TextField prognosis = new TextField("Enter value", 10);
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
            int t = Integer.parseInt(this.time.getSafeTextForParseInt());
            int en = Integer.parseInt(this.energy.getSafeTextForParseInt());
            int no = Integer.parseInt(this.now.getSafeTextForParseInt());
            int p = Integer.parseInt(this.prognosis.getSafeTextForParseInt());
            Task task = new Task(n, en, t, no, p);

            if (Tasks.getTask(n)==null) {
                Tasks.add(task);
                Tasks.doSortUpByProductivity();
                int taskIndex = Tasks.getTaskIndex(n);

                TableModel.getModel().addRow(taskIndex, task.toStringArray());
                optionsList.insertItemAt(n, taskIndex);
            }
            else JOptionPane.showMessageDialog(new JOptionPane(),
                    "Please, change name to another.",
                    "Duplicate names",
                    JOptionPane.WARNING_MESSAGE);
        }

    }

    public void actionChangeButton (ActionEvent e){
        if (!optionsList.getSelectedItem().equals("New task")) {

            String n = this.name.getText();
            boolean isDupliate = false;
            for (int i =0; i< optionsList.getItemCount()-1;i++){
                if (optionsList.getSelectedIndex()==i) i++;
                if (optionsList.getItemAt(i).toString().equals(n)){
                    JOptionPane.showMessageDialog(new JOptionPane(),
                            "Please, change name to another.",
                            "Duplicate names",
                            JOptionPane.WARNING_MESSAGE);
                    isDupliate = true;
                    break;
                }
            }
            if (!isDupliate) {
                int t = Integer.parseInt(this.time.getText());
                int en = Integer.parseInt(this.energy.getText());
                int no = Integer.parseInt(this.now.getText());
                int p = Integer.parseInt(this.prognosis.getText());

                Task temp = Tasks.getTask(Objects.requireNonNull(optionsList.getSelectedItem()).toString());


                temp.setName(n);
                temp.setNowResult(no);
                temp.setEnergyConsumption(en);
                temp.setPrognosisResult(p);
                temp.setTimeConsumption(t);
                Tasks.doSortUpByProductivity();
                int taskIndex = Tasks.getTaskIndex(n);

                optionsList.removeItem(optionsList.getSelectedItem());
                optionsList.insertItemAt(n, taskIndex);

                TableModel.getModel().removeRow(optionsList.getSelectedIndex());
                TableModel.getModel().addRow(taskIndex, temp.toStringArray());
                TableModel.getModel().fireTableRowsUpdated(1, TableModel.getModel().getRowCount());
            }
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
            TableModel.getModel().removeRow(optionsList.getSelectedIndex());
            optionsList.removeItem(optionsList.getSelectedItem());
        }
    }
}
