import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TasksList extends ArrayList<Task> {

    String[][] getArray () {
        String [][] result = new String[this.get(0).toStringArray().length][Task.parametersCount];
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

    public int getTaskIndex (String name){
        int result = 0;
        for (; result < this.size()-1; result++) {
            if (get(result).getName().equals(name))
                return result;
        }
        return result;
    }

    public void doSort (){
        Collections.sort(this,Collections.reverseOrder(Comparator.comparingDouble(Task::getProductivity)));


    }
}
