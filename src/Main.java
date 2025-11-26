import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //MAIN program that offers the user an option selection until the number 9 is pressed
        //Routing with SWITCH
        //Each time we ask the user for the appropriate data,
        // we will perform tests and return a result to the user according to the action they selected.
        Scanner in = new Scanner(System.in);
        TaskRepository repository = new TaskRepository("D:\\תכנות\\migdal-project\\migdal-project\\test.JSON");
        TaskService service = new TaskService(repository);

        System.out.println("** Welcome to the Tasks menu **");
        System.out.println("Please select an option:");
        System.out.println("1. add task");
        System.out.println("2. update task");
        System.out.println("3. delete task");
        System.out.println("4. get all the tasks");
        System.out.println("5. find task");
        System.out.println("6. mark task as done");
        System.out.println("7. search task");
        System.out.println("8. list tasks sorted by status");
        System.out.println("9. exit");
        int opt = in.nextInt();
        in.nextLine();
        String title, description, text;
        int status, id;
        Status st = Status.IN_PROGRESS;

        while (opt != 9) {
            switch (opt) {
                case 1:
                    System.out.println("enter the title");
                    title = in.nextLine();
                    System.out.println("enter the description");
                    description = in.nextLine();
                    repository.add(title, description, Status.NEW);
                    System.out.println("your task is add to the list");
                    break;
                case 2:
                    System.out.println("enter the id of the task to update:");
                    id = in.nextInt();
                    in.nextLine();
                    if (repository.getById(id) == null)
                        System.out.println("there is not task with this id");
                    else {
                        System.out.println("enter the new title");
                        title = in.nextLine();
                        System.out.println("enter the description");
                        description = in.nextLine();
                        System.out.println("enter the status: 1 =NEW, 2= IN_PROGRESS, 3= DONE");
                        status = in.nextInt();
                        in.nextLine();
                        switch (status) {
                            case 1:
                                st = Status.NEW;
                                break;
                            case 2:
                                st = Status.IN_PROGRESS;
                                break;
                            case 3:
                                st = Status.DONE;
                                break;
                        }
                        Task nt = new Task(id, title, description, st);
                        repository.update(nt);
                        System.out.println("update success!");
                    }
                    break;
                case 3:
                    System.out.println("enter id to delete");
                    id = in.nextInt();
                    in.nextLine();
                    repository.delete(id);
                    break;
                case 4:
                    System.out.println("the tasks list:");
                    for (Task task : repository.listAll())
                        System.out.println(task);
                    break;
                case 5:
                    System.out.println("enter id to found:");
                    id = in.nextInt();
                    in.nextLine();
                    Task t = repository.getById(id);
                    if (t != null)
                        System.out.println(t);
                    else
                        System.out.println("task is not found");
                    break;
                case 6:
                    System.out.println("enter id to mark the task as done:");
                    id = in.nextInt();
                    in.nextLine();
                    service.markAsDone(id);
                    break;
                case 7:
                    System.out.println("enter the title or the description to search");
                    text = in.nextLine();
                    List<Task> lt = service.search(text);
                    if (lt.isEmpty())
                        System.out.println("there is not results");
                    else
                        for (Task task : lt)
                            System.out.println(task);
                    break;
                case 8:
                    System.out.println("sorted by status:");
                    for (Task task: service.sortListByStatus())
                        System.out.println(task);
                    break;


            }
            System.out.println("Please select an option:");
            opt = in.nextInt();
            in.nextLine();
        }


    }
}
