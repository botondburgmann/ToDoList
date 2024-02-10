import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void completeTask(int index) {
        if (index >= tasks.size() || index < 0) {
            System.err.println("Error: Invalid index");
            return;
        }
        if (tasks.get(index).isCompleted()) {
            System.err.println("Error: this task is already completed");
            return;
        }
        tasks.get(index).setCompleted(true);
    }

    public void displayTasks() {
        if (tasks.size() == 0) {
            System.out.println("You have no tasks currently");
            return;
        }
        System.out.println("Task    Due date    Status");
        for (Task task : tasks) {
            System.out.print(task.getTaskName() + "    " + task.getDueDate() + "    ");
            if (task.isCompleted()) {
                System.out.println("completed");
            } else {
                System.out.println("pending");
            }
        }
    }

    public int getSize() {
        return tasks.size();
    }
}
