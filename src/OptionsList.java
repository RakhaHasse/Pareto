import javax.swing.*;
import java.awt.*;

public class OptionsList extends JComboBox {
    TextField [] textFields; // TextField[]{name,energy,time,now,prognosis}
    TasksList tasks;
    Description description;
    Task emptyTask;
    String[] optionsNames;
    public OptionsList (TasksList tasks, TextField[] textFields,
                        String[] optionsNames, String addTaskName, Description description){
        super(optionsNames);
        this.textFields = textFields;
        this.tasks=tasks;
        this.emptyTask = new Task(addTaskName);
        this.optionsNames=optionsNames;
        this.description = description;
    }

    public void setChosenOptionText(Task task){
        if (!task.getName().equals(emptyTask.getName())) {
            textFields[0].setText(task.getName());
            textFields[1].setText("" + task.getEnergyConsumption());
            textFields[2].setText("" + task.getTimeConsumption());
            textFields[3].setText("" + task.getNowResult());
            textFields[4].setText("" + task.getPrognosisResult());
            description.setText(task.getDescription());
        }
    }

    @Override
    protected void selectedItemChanged() {
        super.selectedItemChanged();
        if (!super.getSelectedItem().toString().equals(emptyTask.getName()))
            setChosenOptionText(tasks.getTask(super.getSelectedItem().toString()));
        else resetAndPlaceholder();
    }

    void resetAndPlaceholder(){
        for (int i = 0; i < textFields.length; i++) {
            textFields[i].resetAndPlaceholder();
            description.resetAndPlaceholder();
        }
    }
}
