import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskManagementSystem {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nTask Management System:");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. List Tasks");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1 -> addTask();
                case 2 -> removeTask();
                case 3 -> listTasks();
                case 4 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

    private static void addTask() {
        System.out.print("Enter task name: ");
        String name = scanner.nextLine();

        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        System.out.print("Enter due date (YYYY-MM-DD): ");
        String dueDate = scanner.nextLine();

        Task task = new Task(name, description, dueDate);
        tasks.add(task);
        System.out.println("Task added successfully.");
    }

    private static void removeTask() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to remove.");
            return;
        }

        System.out.println("Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).getName());
        }

        System.out.print("Enter index of task to remove: ");
        int index = getIntInputInRange(1, tasks.size());

        if (index >= 1 && index <= tasks.size()) {
            Task removedTask = tasks.remove(index - 1);
            System.out.println("Task removed: " + removedTask.getName());
        } else {
            System.out.println("Invalid index. No task removed.");
        }
    }

    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to display.");
        } else {
            System.out.println("Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println("Name: " + task.getName());
                System.out.println("Description: " + task.getDescription());
                System.out.println("Due Date: " + task.getDueDate());
                System.out.println();
            }
        }
    }

    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    private static int getIntInputInRange(int min, int max) {
        while (true) {
            int input = getIntInput();
            if (input < min || input > max) {
                System.out.print("Invalid input. Please enter a number between " + min + " and " + max + ": ");
            } else {
                return input;
            }
        }
    }
}
