import java.util.List;

public class Main {
    public static void main(String[] args) {
        TaskRepository tr = new TaskRepository("D:\\jjjj\u200F\u200F.JSON");
        Task t = new Task(14,"avreimy","farkash",Status.IN_PROGRESS);
        Task t2 = new Task(15,"avreimy","farkash",Status.IN_PROGRESS);
        Task t3 = new Task(16,"avreimy","farkash",Status.IN_PROGRESS);
        Task t4 = new Task(17,"avreimy","farkash",Status.IN_PROGRESS);
        tr.delete(10);
        TaskService ts = new TaskService(tr);
        ts.markAsDone(13);
        List<Task> ttt = ts.sortListByStatus();
        for (Task tt: ttt){
            System.out.println(tt);
        }

    }
}
