import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TasksList extends ArrayList<Task> {

    String[][] getArray () {
        String [][] result = new String[this.size()][Task.parametersCount];
        for (int i =0; i<this.size();i++){
            result[i]=this.get(i).toStringArray();
        }
        return result;
    }



    public Task getTask (String name){
        Task result = null;
        for (Task task : this) {
            if (task.getName().equals(name))
                result = task;
        }
        return result;
    }


    public int getTaskIndex (String name){

          for (int result = 0; result < this.size(); result++){
            if (this.get(result).getName().equals(name))
                return result;
          }
        return -1;
    }

    public void doSortUpByProductivity(){
        Collections.sort(this,Collections.reverseOrder(Comparator.comparingDouble(Task::getProductivity)));
        if (!this.isEmpty() & !TableModel.getModel().isEmpty()){
            for (int i = 0; i < size(); i++) {
                String taskName = TableModel.getModel().getValueAt(i,0); // column = 0, as name of task
                int position = getTaskIndex(taskName);
                if (i!= position)TableModel.getModel().setTaskIndex(i,position);
            }

        }
    }
}
