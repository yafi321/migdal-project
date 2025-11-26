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
    private static int cntTask = 1;


    public TaskRepository(String filePath) {
        //A constructor that accepts a file location and initializes the task list using the function
        this.filePath = filePath;
        this.taskList = loadTasksFromFile();// Loading the file into the list
    }

    private String readTheFile() {
        //A function that reads the file and returns a string with the contents
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
        //A function that accepts a string and writes it to a file
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
                //When I first load the file, I save the last ID number to
                // create an ID that doesn't already exist next time.
                cntTask = id+1;
                list.add(new Task(id, title, description, status));//add to the list
            }


        }
        return list;
    }


    public void add(String title, String description, Status status) {
        //An add function that receives task data creates it,
        // adds it to the list, and rewrites the file with the new task.
        Task newTask = new Task(cntTask, title, description, status);
        taskList.add(newTask);
        cntTask++;
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
        //A delete function that searches for the location of the task,
        // deletes it from the list, and updates and rewrites the file accordingly.
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
        //An update function that receives a task, goes through the task list,
        // finds the task and updates it, and then rewrites the updated tasks to the file.
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
        //A function that receives a task ID, iterates over the list and
        // when found returns the task, if not found returns NULL
        for (Task task : taskList) {
            if (id == task.getId())
                return task;
        }
        return null;
    }

    public List<Task> listAll() {
        //A function that returns the entire list.
        return taskList;
    }


}
