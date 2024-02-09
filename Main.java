import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        int option;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Choose from the following options");
            System.out.println("1: Add a new task");
            System.out.println("2: Complete a task");
            System.out.println("3: Display all tasks");
            System.out.println("4: Exit");
            option = input.nextInt();
            switch (option) {
                case 1 -> {
                    input.nextLine();
                    System.out.println("Task name: ");
                    String taskName = input.nextLine();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    System.out.println("Due date (yyy-MM-dd): ");
                    String dueDateString = input.nextLine();
                    LocalDate dueDate = LocalDate.parse(dueDateString, formatter);
                    Task task = new Task(taskName, dueDate, false);
                    taskManager.addTask(task);
                }
                case 2 -> {
                    if (taskManager.getSize() == 0) {
                        System.out.println("You have no tasks currently");
                        break;
                    }
                    System.out.println("Which task would you like to complete? Give its index");
                    int index = input.nextInt();
                    taskManager.completeTask(index);
                }
                case 3 -> {
                    taskManager.displayTasks();
                }
                case 4 -> {
                    System.out.println("Exiting program...");
                }
                default -> {
                    System.out.println("Invalid option");
                }
            }
        } while (option != 4);
        input.close();
    }
}