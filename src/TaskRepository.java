import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private final List<Task> taskList;
    private final String filePath;
    private static int cntTaks;


    public TaskRepository(String filePath) {
        //A constructor that accepts a file location and initializes the task list using the function
        this.filePath = filePath;
        this.taskList = loadTasksFromFile();
        cntTaks = taskList.size()+1;
    }

    private String readTheFile() {
        String json = null;
        File file = new File(this.filePath);
        if (!file.exists()) {
            System.out.println("File not found: " + this.filePath);
            return null;
        }
        try {
            json = new String(Files.readAllBytes(Paths.get(this.filePath)));
        } catch (IOException e) {
            //Catch in case it failed to read the file
            System.out.println("en error with read the file: " + e.getMessage());
        }
        return json;
    }

    private void writeToFile(String newContent) {
        File file = new File(this.filePath);
        try {
            Files.write(Paths.get(this.filePath), newContent.getBytes());
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }


    // A function that exports the JSON file to a task list property.
    private List<Task> loadTasksFromFile() {
        List<Task> list = new ArrayList<>();

        String jsonTasks = readTheFile();
        if (jsonTasks.equals(""))
            return new ArrayList<>();
        if (jsonTasks != null) {
            jsonTasks = jsonTasks.trim();
            jsonTasks = jsonTasks.substring(1, jsonTasks.length() - 1).trim();
            String[] tasks = jsonTasks.split("\\},\\s*\\{");//Splitting into an array of tasks
            int id = 0;
            String title = "", description = "";
            Status status = Status.NEW;
            for (String task : tasks) {//Go over each task
                task = task.replace("{", "").replace("}", "").trim();
                String[] fields = task.split(",");
                id = 0;
                title = "";
                description = "";
                status = Status.NEW;

                for (String field : fields) {//Go over each field
                    String[] keyValue = field.split(":");
                    String key = keyValue[0].replace("\"", "").trim();
                    String value = keyValue[1].replace("\"", "").trim();
                    switch (key) {
                        case "id":
                            id = Integer.parseInt(value);
                            break;
                        case "title":
                            title = value;
                            break;
                        case "description":
                            description = value;
                            break;
                        case "status":
                            status = Status.valueOf(value);
                            break;
                    }
                }
                list.add(new Task(id, title, description, status));//add to the list
            }


        }
        return list;
    }


    public void add(String title, String description, Status status) {
        Task newTask = new Task(cntTaks, title, description, status);
        taskList.add(newTask);
        cntTaks++;
        String jsonTasks = "[\n" + readTheFile();
        if (jsonTasks != null) {
            jsonTasks = jsonTasks.substring(1, jsonTasks.length() - 1).trim();

            jsonTasks = jsonTasks + ",\n";
            jsonTasks += newTask.toString();
            jsonTasks += "\n]";
            writeToFile(jsonTasks);

        }

    }

    public void delete(int id) {
        int cnt = -1;
        int cntToDelete = -1;
        for (Task task : taskList) {
            cnt++;
            if (task.getId() == id) {
                cntToDelete = cnt;
            }
        }
        if (cntToDelete == -1)
            System.out.println("the id is not found");
        else {
            taskList.remove(cntToDelete);
            String str = "[\n";
            for (Task task : taskList) {
                str += task.toString() + ",\n";
            }
            str = str.substring(0, str.length() - 2);
            str += "\n]";
            writeToFile(str);
            System.out.println("delete success!");
        }
    }

    public void update(Task task) {
        for (Task t : taskList) {
            if (t.getId() == task.getId()) {
                t.setTitle(task.getTitle());
                t.setDescription(task.getDescription());
                t.setStatus(task.getStatus());
            }
        }
        String str = "[\n";
        for (Task t : taskList) {
            str += t.toString() + ",\n";
        }
        str = str.substring(0, str.length() - 2);
        str += "\n]";
        writeToFile(str);
    }

    public Task getById(int id) {
        for (Task task : taskList) {
            if (id == task.getId())
                return task;
        }
        return null;
    }

    public List<Task> listAll() {
        return taskList;
    }


}
