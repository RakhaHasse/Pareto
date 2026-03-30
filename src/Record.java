import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Record extends JPanel {
    final String addTaskOptionName = "New task",
            descriptionPlaceholder ="Enter task description here",
            digitsPlaceHolder = "Enter value",
            namePlaceHolder = "Enter task name";
    final int intParameterTextFields = 10,
            intConstraintsGridXLabel = 9,
            intContstraintsGridXTextField = 10;
    TextField  name;
    Description decription;
    TasksList tasksList;
    DigitsTextField energy, time, now, prognosis;
    OptionsList optionsList;
    TasksRepository repository;
    public Record(TasksList tasksList, TasksRepository tasksRepository){
        super(new GridBagLayout());
        repository=tasksRepository;
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        this.tasksList = tasksList;

    String[] optionsToChoose = new String [this.tasksList.size()+1];
        gridBagConstraints.gridheight=1;
        gridBagConstraints.gridwidth =1;
        gridBagConstraints.gridy = -8;
        gridBagConstraints.gridx = -1;
        JLabel descriptionD = new JLabel("Task description");
        this.add(descriptionD, gridBagConstraints);
        gridBagConstraints.gridy = -3;
        gridBagConstraints.gridx = -3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight =12;
        this.decription = new Description(descriptionPlaceholder);
        this.add(decription, gridBagConstraints);
        gridBagConstraints.gridheight=1;
gridBagConstraints.gridwidth =1;
    JLabel nameD = new JLabel( "Task name");
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridx = intConstraintsGridXLabel;
        super.add(nameD,gridBagConstraints);
    name = new TextField(namePlaceHolder, intParameterTextFields);
        this.setName(name.getText());
    gridBagConstraints.gridx = intContstraintsGridXTextField;
        this.add(name,gridBagConstraints);

    gridBagConstraints.gridx = intConstraintsGridXLabel;
    gridBagConstraints.gridy = 1;
    JLabel timeD = new JLabel("Time consumption:");
        this.add(timeD, gridBagConstraints);
    gridBagConstraints.gridx = intContstraintsGridXTextField;
        this.time =new DigitsTextField(digitsPlaceHolder, intParameterTextFields);
        this.add(time, gridBagConstraints);

    gridBagConstraints.gridy = 2;
    gridBagConstraints.gridx = intConstraintsGridXLabel;
    JLabel energyD = new JLabel("Energy consumption");
        this.add(energyD, gridBagConstraints);
    gridBagConstraints.gridx = intContstraintsGridXTextField;

        this.energy = new DigitsTextField(digitsPlaceHolder, intParameterTextFields);
        this.add(energy, gridBagConstraints);

    gridBagConstraints.gridy = 3;
    gridBagConstraints.gridx = intConstraintsGridXLabel;
    JLabel nowD = new JLabel("Result now:");
    this.add(nowD, gridBagConstraints);
    gridBagConstraints.gridx = intContstraintsGridXTextField;
    this.now = new DigitsTextField(digitsPlaceHolder, intParameterTextFields);
    this.add(now, gridBagConstraints);
    gridBagConstraints.gridy = 4;
    gridBagConstraints.gridx = intConstraintsGridXLabel;
    JLabel prognosisD = new JLabel("Prognosis result:");
    this.add(prognosisD, gridBagConstraints);
    gridBagConstraints.gridx = intContstraintsGridXTextField;
    this.prognosis =new DigitsTextField(digitsPlaceHolder, intParameterTextFields);
    this.add(prognosis, gridBagConstraints);

    if (!this.tasksList.isEmpty())
            for (int i = 0; i < optionsToChoose.length-1; i++) {
                optionsToChoose[i]=tasksList.get(i).getName();
            }
        optionsToChoose[optionsToChoose.length-1] = addTaskOptionName;
        OptionsList list = new OptionsList(this.tasksList,
                new TextField[]{name,energy,time,now,prognosis},optionsToChoose,addTaskOptionName, this.decription);
    gridBagConstraints.gridy = 5;
    gridBagConstraints.gridx = intConstraintsGridXLabel;
    gridBagConstraints.gridwidth =2;
    list.setBounds(80, 50, 140, 20);
    this.add(list, gridBagConstraints);
    optionsList=list;

    JButton addTask = new JButton("Add task");
    gridBagConstraints.gridy = 6;
    gridBagConstraints.gridx=intConstraintsGridXLabel;
    gridBagConstraints.gridwidth=1;
    this.add(addTask,gridBagConstraints);
    addTask.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        actionAddButton(e);
                    }
                });

    JButton changeTask = new JButton("Change task");
    gridBagConstraints.gridy = 6;
    gridBagConstraints.gridx=intContstraintsGridXTextField;
    gridBagConstraints.gridwidth=1;
    this.add(changeTask,gridBagConstraints);
    changeTask.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        actionChangeButton(e);
                    }
                });

    JButton deleteTask = new JButton("Delete task");
    gridBagConstraints.gridx = 11;
    this.add(deleteTask,gridBagConstraints);
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

        if (!optionsList.getSelectedItem().toString().equals(addTaskOptionName)) {
            tasksList.remove(tasksList.getTask(optionsList.getSelectedItem().toString()));
            TableModel.getModel().removeRow(optionsList.getSelectedIndex());
            optionsList.removeItem(optionsList.getSelectedItem());
        }
        repository.saveTasks(tasksList);
    }

    public void actionChangeButton(ActionEvent e) {
        if (!optionsList.getSelectedItem().equals(addTaskOptionName)) {
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
                Task temp = tasksList.getTask(Objects.requireNonNull(optionsList.getSelectedItem()).toString());
                temp.setName(n);
                temp.setNowResult(values[2]);
                temp.setEnergyConsumption(values[1]);
                temp.setPrognosisResult(values[3]);
                temp.setTimeConsumption(values[0]);
                temp.setDescription(decription.getText());

                TableModel.getModel().updateRow(optionsList.getSelectedItem().toString(),temp.toStringArray());
                tasksList.doSortUpByProductivity();
                int taskIndex = tasksList.getTaskIndex(n);
                optionsList.removeItem(optionsList.getSelectedItem());
                optionsList.insertItemAt(n, taskIndex);

                TableModel.getModel().fireTableRowsUpdated(1, TableModel.getModel().getRowCount());
            }
        }
        repository.saveTasks(tasksList);
    }
    public void actionAddButton(ActionEvent e) {
        if (optionsList.getSelectedItem().equals(addTaskOptionName)) {
            String n = name.getText();
            if (n.isEmpty()) {
                JOptionPane.showMessageDialog(new JOptionPane(),
                        "Please, fill name.",
                        "Empty task name",
                        JOptionPane.WARNING_MESSAGE);
                return;
            } else if (n.equals(namePlaceHolder)) {
                JOptionPane.showMessageDialog(new JOptionPane(),
                        "Please, fill name.",
                        "Incorrect input task name",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            int[] temp = readTaskValues();
            Task task = new Task(n, temp[0], temp[1], temp[2], temp[3],decription.getText());

            if (tasksList.getTask(n) == null) {
                {//adding element to TableModel & TasksList
                    tasksList.add(task);
                    TableModel.getModel().addRow(task.toStringArray());
                }
                tasksList.doSortUpByProductivity();
                int taskIndex = tasksList.getTaskIndex(n);


                optionsList.insertItemAt(n, taskIndex);
            } else JOptionPane.showMessageDialog(new JOptionPane(),
                    "Please, change name to another.",
                    "Duplicate names",
                    JOptionPane.WARNING_MESSAGE);
        }
        {//final method operations
            tasksList.doSortUpByProductivity();
            repository.saveTasks(tasksList);
            optionsList.resetAndPlaceholder();

        }
    }

}