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

}
