import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Record extends JPanel {
    final String zeroOption = "New task";
    TextField  name;
    TasksList Tasks;
    DigitsTextField energy, time, now, prognosis;
    OptionsList optionsList;
    public Record(TasksList tasks){
    super(new GridBagLayout());
    Tasks = tasks;
    GridBagConstraints constraints = new GridBagConstraints();

    JLabel nameD = new JLabel( "Task name");
    constraints.gridx =0;
    constraints.gridy =0;
        super.add(nameD,constraints);
    name = new TextField("Enter task name", 10);
        this.setName(name.getText());
    constraints.gridx = 1;
        this.add(name,constraints);

    constraints.gridx = 0;
    constraints.gridy = 1;
    JLabel timeD = new JLabel("Time consumption:");
        this.add(timeD, constraints);
    constraints.gridx = 1;
        this.time =new DigitsTextField();
        this.add(time, constraints);

    constraints.gridy = 2;
    constraints.gridx = 0;
    JLabel energyD = new JLabel("Energy consumption");
        this.add(energyD, constraints);
    constraints.gridx = 1;

        this.energy = new DigitsTextField();
        this.add(energy, constraints);

    constraints.gridy = 3;
    constraints.gridx = 0;
    JLabel nowD = new JLabel("Result now:");
        this.add(nowD, constraints);
    constraints.gridx = 1;

        this.now = new DigitsTextField();;
        this.add(now, constraints);

    constraints.gridy = 4;
    constraints.gridx = 0;
    JLabel prognosisD = new JLabel("Prognosis result:");
        this.add(prognosisD, constraints);
    constraints.gridx = 1;
        this.prognosis = new DigitsTextField();
        this.add(prognosis, constraints);

    constraints.gridy = 5;
    constraints.gridx = 0;
    constraints.gridwidth =2;
    String[] optionsToChoose = {zeroOption};
    OptionsList list = new OptionsList(Tasks, new TextField[]{name,energy,time,now,prognosis},optionsToChoose);
        list.setBounds(80, 50, 140, 20);
       this.add(list, constraints);
    optionsList=list;

    JButton addTask = new JButton("Add task");
    constraints.gridy = 6;
    constraints.gridx=0;
    constraints.gridwidth=1;
    this.add(addTask,constraints);
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
        this.add(changeTask,constraints);
        changeTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionChangeButton(e);
            }
        });

    JButton deleteTask = new JButton("Delete task");
    constraints.gridx = 2;
        this.add(deleteTask,constraints);
        deleteTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionDeleteTask(e);
            }

        });
    }
    private int[] readTaskValues() {
        return new int[]{
                Integer.parseInt(time.getSafeTextForParseInt()),
                Integer.parseInt(energy.getSafeTextForParseInt()),
                Integer.parseInt(now.getSafeTextForParseInt()),
                Integer.parseInt(prognosis.getSafeTextForParseInt())
        };
    }
    public void actionDeleteTask(ActionEvent e) {

        if (!optionsList.getSelectedItem().toString().equals(zeroOption)) {
            Tasks.remove(Tasks.getTask(optionsList.getSelectedItem().toString()));
            TableModel.getModel().removeRow(optionsList.getSelectedIndex());
            optionsList.removeItem(optionsList.getSelectedItem());
        }
    }

    public void actionChangeButton(ActionEvent e) {
        if (!optionsList.getSelectedItem().equals(zeroOption)) {

            String n = name.getText();
            boolean isDupliate = false;
            for (int i = 0; i < optionsList.getItemCount() - 1; i++) {
                if (optionsList.getSelectedIndex() == i) i++;
                if (optionsList.getItemAt(i).toString().equals(n)) {
                    JOptionPane.showMessageDialog(new JOptionPane(),
                            "Please, change name to another.",
                            "Duplicate names",
                            JOptionPane.WARNING_MESSAGE);
                    isDupliate = true;
                    break;
                }
            }
            if (!isDupliate) {
                int[] values = readTaskValues();

                Task temp = Tasks.getTask(Objects.requireNonNull(optionsList.getSelectedItem()).toString());


                temp.setName(n);
                temp.setNowResult(values[2]);
                temp.setEnergyConsumption(values[1]);
                temp.setPrognosisResult(values[3]);
                temp.setTimeConsumption(values[0]);
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
    public void actionAddButton(ActionEvent e) {
        if (optionsList.getSelectedItem().equals(zeroOption)) {
            String n = name.getText();
            int[] temp = readTaskValues();
            Task task = new Task(n, temp[0], temp[1], temp[2], temp[3]);

            if (Tasks.getTask(n) == null) {
                Tasks.add(task);
                Tasks.doSortUpByProductivity();
                int taskIndex = Tasks.getTaskIndex(n);

                TableModel.getModel().addRow(taskIndex, task.toStringArray());
                optionsList.insertItemAt(n, taskIndex);
            } else JOptionPane.showMessageDialog(new JOptionPane(),
                    "Please, change name to another.",
                    "Duplicate names",
                    JOptionPane.WARNING_MESSAGE);
        }

    }

}