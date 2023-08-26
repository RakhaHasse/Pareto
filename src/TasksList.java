import java.util.ArrayList;

public class TasksList extends ArrayList<Task> {
    String[][] getArray () {
        String [][] result = new String[this.get(0).toStringArray().length][this.size()+1];
        System.arraycopy(columnNames,0,result[0],0,columnNames.length);
        for (int i =1; i<this.size();i++){
            String [] temp = this.get(i).toStringArray();
            System.arraycopy(temp, 0, result[i], 0, temp.length);
        }
        return result;
    }

    String[] columnNames = {
            "Task name",
            "Time",
            "Energy",
            "Consumption",
            "Moment result",
            "Prognosis",
            "Result",
            "Productivity"
    };

    public Task getTask (String name){
        Task result = null;
        for (Task task : this) {
            if (task.getName().equals(name))
                result = task;
        }
        return result;
    }
}
