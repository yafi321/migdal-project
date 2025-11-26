import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;

    }

    public void markAsDone(int taskId){
        //A function that receives a task ID, looks for it in the list,
        // updates the status to DONE, and calls the update method.
        Task result = repository.getById(taskId);
        if (result == null)
            System.out.println("the task is not found");
        else {
            Task nt = new Task(taskId, result.getTitle(), result.getDescription(), Status.DONE);
            repository.update(nt);
        }
    }

    public List<Task> search(String text){
        //An action that accepts a text string, iterates over the list of tasks and,
        // if the text appears in the title or description,
        // returns a list of all tasks that meet this condition.
        List<Task> result = new ArrayList<>();
        for (Task task: repository.listAll()){
            if (task.getTitle().equals(text) || task.getDescription().equals(text)){
                result.add(task);
            }
        }
        return result;
    }

}
