import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Main
 */
public class Main {

    private static boolean isValidDateFormat(String dateString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(dateString, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        int option = 0;
        try (Scanner input = new Scanner(System.in)) {
            do {
                System.out.println("Choose from the following options");
                System.out.println("1: Add a new task");
                System.out.println("2: Complete a task");
                System.out.println("3: Display all tasks");
                System.out.println("4: Exit");
                if (!input.hasNextInt()) {
                    System.err.println("Error: Invalid input. Please enter a valid option.");
                    input.next();
                } else {
                    option = input.nextInt();
                    switch (option) {
                        case 1 -> {
                            input.nextLine();
                            System.out.println("Task name: ");
                            String taskName = input.nextLine();

                            System.out.println("Due date (yyyy-MM-dd): ");
                            String dueDateString = input.nextLine();

                            if (isValidDateFormat(dueDateString)) {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                LocalDate dueDate = LocalDate.parse(dueDateString, formatter);
                                Task task = new Task(taskName, dueDate, false);
                                taskManager.addTask(task);
                            } else {
                                System.err.println("Error: Invalid date format.");
                            }

                        }
                        case 2 -> {
                            if (taskManager.getSize() == 0) {
                                System.out.println("You have no tasks currently");
                                break;
                            }
                            taskManager.displayTasks();
                            System.out.println("Which task would you like to complete? Give its index");
                            int index;
                            if (!input.hasNextInt()) {
                                System.out.println("Error: Invalid input. Please enter a valid index.");
                                input.next();
                            } else {
                                index = input.nextInt();
                                taskManager.completeTask(index);
                            }
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
                }
            } while (option != 4);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}