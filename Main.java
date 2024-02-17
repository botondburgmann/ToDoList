import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

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

    private static int getValidIntegerInput(String prompt, Scanner scanner) {
        while (true) {
            System.out.println(prompt);
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (input > 0 && input < 5) {
                    scanner.nextLine();
                    return input;
                } else {
                    System.err.println("Error: Invalid input. Please enter a valid option.");
                    scanner.nextLine();
                }
            } else {
                System.err.println("Error: Invalid input. Please enter an integer.");
                scanner.nextLine();
            }
        }
    }

    public static String prompt() {
        return """
                Choose from the following options:
                1: Add a new task
                2: Complete a task
                3: Display all tasks
                4: Exit""";
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        int option = 0;
        Scanner input = new Scanner(System.in);

        do {
            option = getValidIntegerInput(prompt(), input);
            switch (option) {
                case 1 -> {
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

        } while (option != 4);
        input.close();
    }
}