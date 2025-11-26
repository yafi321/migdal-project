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

    public List<Task> sortListByStatus(){
        //An action that iterates over the list of tasks,
        // goes through the status of each task and associates it with the list accordingly,
        // finally returning a unified, sorted list of all tasks.
        List<Task> newList = new ArrayList<>();
        List<Task> inProgress = new ArrayList<>();
        List<Task> done = new ArrayList<>();

        for (Task task: repository.listAll()){
            switch (task.getStatus()){
                case Status.NEW:
                    newList.add(task);
                    break;
                case Status.IN_PROGRESS:
                    inProgress.add(task);
                    break;
                case Status.DONE:
                    done.add(task);
            }

        }
        List<Task> result = new ArrayList<>();
        result.addAll(newList);
        result.addAll(inProgress);
        result.addAll(done);
        return result;
    }


}
