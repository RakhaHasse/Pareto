import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TasksRepository {
    ObjectWriter writer;
    ObjectReader reader;
    File defaultTaskList;
    final String filePath = "tasks-list.json";
    TasksRepository (){
        TableModel.getModel();
        // Create prepared ObjectWriter & ObjectReader
        ObjectMapper mapper = new ObjectMapper();
        writer = mapper.writer(new DefaultPrettyPrinter());
        reader = mapper.readerFor(mapper.getTypeFactory().
                constructCollectionType(ArrayList.class,Task.class));
        ;
        //File Settings
        defaultTaskList = new File(filePath);
    }

    public void saveTasks (TasksList tasksList){
        writeTasksIntoFile(tasksList);
    }

    private void writeTasksIntoFile (TasksList tasksList){
        try {
            writer.writeValue(defaultTaskList,tasksList);
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    public TasksList loadTasks (){
        TasksList result = new TasksList();
        ArrayList<Task>rawData = this.readTasksFromFile();
        if (rawData!=null)
            for (Task savedTask:
                rawData) {
                result.add(savedTask);
                TableModel.getModel().addRow(savedTask.toStringArray());
            }
        result.doSortUpByProductivity();
        return result;

    }

    private ArrayList<Task> readTasksFromFile() {
            ArrayList<Task> rawData;
            if (defaultTaskList.exists()){
            try {
                rawData = reader.readValue(defaultTaskList);
            }
            catch (MismatchedInputException miss){
                return null;
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            return rawData;
            }
        return null;
    }
}
