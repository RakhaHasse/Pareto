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
        int result = 0;
          {
            if (this.get(result).getName().equals(name))
                return result;
            result++;
        } while (result < this.size()-1);
        return -1;
    }

    public void doSortUpByProductivity(){
        Collections.sort(this,Collections.reverseOrder(Comparator.comparingDouble(Task::getProductivity)));



    }
}
