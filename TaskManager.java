import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void completeTask(int index) {
        tasks.get(index).setCompleted(true);
        ;
    }

    public void displayTasks() {
        if (tasks.size() == 0) {
            System.out.println("No tasks to display");
            return;
        }
        for (Task task : tasks) {
            System.out.println("Task: " + task.getTaskName());
            System.out.println("Due date: " + task.getDueDate());
            if (task.isCompleted()) {
                System.out.println("Status: completed");
            } else {
                System.out.println("Status: pending");
            }
        }
    }

    public int getSize() {
        return tasks.size();
    }
}
