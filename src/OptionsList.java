import javax.swing.*;

public class OptionsList<String>extends JComboBox {
    TextField [] textFields; // TextField[]{name,energy,time,now,prognosis}
    TasksList tasks;
    Task emptyTask;
    String[] optionsNames;
    public OptionsList (TasksList tasks, TextField[]textFields, String[]optionsNames){
        super(optionsNames);
        this.textFields = textFields;
        this.tasks=tasks;
        this.emptyTask = new Task("New task");
        this.optionsNames=optionsNames;
    }

    public void setChoseOptionText(Task task){
        textFields[0].setText(task.getName());
        textFields[1].setText(""+task.getEnergyConsumption());
        textFields[2].setText(""+task.getTimeConsumption());
        textFields[3].setText(""+task.getNowResult());
        textFields[4].setText(""+task.getPrognosisResult());
    }

    @Override
    protected void selectedItemChanged() {
        super.selectedItemChanged();
        setChoseOptionText(super.getSelectedItem().toString().equals("New task")?
                emptyTask:
                tasks.getTask(super.getSelectedItem().toString()));
    }
}
