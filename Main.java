import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

interface MyFunction {
    boolean apply(int input, int min, int max);
}

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

    private static int getValidIntegerInput(String prompt, Scanner scanner, MyFunction validation, int min, int max) {
        while (true) {
            System.out.println(prompt);
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (validation.apply(input, min, max)) {
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

    private static boolean isInRange(int input, int min, int max) {
        return (input >= min && input <= max);
    }

    private static String prompt() {
        return """
                Choose from the following options:
                1: Add a new task
                2: Complete a task
                3: Display all tasks
                4: Exit""";
    }

    private static Task createTask(String taskName, String dueDateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dueDate = LocalDate.parse(dueDateString, formatter);
        return new Task(taskName, dueDate, false);
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        int option = 0;
        Scanner input = new Scanner(System.in);

        do {
            option = getValidIntegerInput(prompt(), input, Main::isInRange, 1, 5);
            switch (option) {
                case 1 -> {
                    System.out.println("Task name: ");
                    String taskName = input.nextLine();

                    System.out.println("Due date (yyyy-MM-dd): ");
                    String dueDateString = input.nextLine();

                    if (isValidDateFormat(dueDateString))
                        taskManager.addTask(createTask(taskName, dueDateString));
                    else
                        System.err.println("Error: Invalid date format.");

                }
                case 2 -> {
                    if (taskManager.getSize() == 0) {
                        System.out.println("You have no tasks currently");
                        break;
                    }
                    taskManager.displayTasks();
                    int index = getValidIntegerInput("Which task would you like to complete? Give its index", input,
                            Main::isInRange, 0, taskManager.getSize());
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