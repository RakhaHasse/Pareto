import javax.swing.*;

public class OptionsList extends JComboBox {
    TextField [] textFields; // TextField[]{name,energy,time,now,prognosis}
    TasksList tasks;
    Task emptyTask;
    String[] optionsNames;
    public OptionsList (TasksList tasks, TextField[] textFields, String[] optionsNames){
        super(optionsNames);
        this.textFields = textFields;
        this.tasks=tasks;
        this.emptyTask = new Task(optionsNames[0]);
        this.optionsNames=optionsNames;
    }

    public void setChosenOptionText(Task task){
        if (!task.getName().equals(emptyTask.getName())) {
            textFields[0].setText(task.getName());
            textFields[1].setText("" + task.getEnergyConsumption());
            textFields[2].setText("" + task.getTimeConsumption());
            textFields[3].setText("" + task.getNowResult());
            textFields[4].setText("" + task.getPrognosisResult());
        }
    }

    @Override
    protected void selectedItemChanged() {
        super.selectedItemChanged();
        if (!super.getSelectedItem().toString().equals(emptyTask.getName()))
            setChosenOptionText(tasks.getTask(super.getSelectedItem().toString()));
    }
}
