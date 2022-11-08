import tasks.Task;
import tasks.TaskTimeComparator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Calendar {
    private final Map<Integer, Task> tasks = new HashMap<>();

    public void removeTask(int id) {
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            if (entry.getValue().getId() == id) {
                System.out.println("Задача удалена.");
                tasks.remove(id);
                return;
            }
        }
    }

    public Collection<Task> getTasksForTheDay(LocalDate date) {
        Set<Task> tasksForDay = new TreeSet<>(new TaskTimeComparator());
        for (Task task : tasks.values()) {
            if (task.appearsIn(date)) {
                tasksForDay.add(task);
            }
        }
        return tasksForDay;
    }

    public static void manageTasks() {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            // todo: обрабатываем пункт меню 2
                            break;
                        case 3:
                            // todo: обрабатываем пункт меню 3
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void inputTask(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String taskName = scanner.next();
        // todo
    }

    private static void printMenu() {
        System.out.printf("1. Добавить задачу\n" +
                "2. Удалить задачу\n" +
                "3. Получить задачу на указанный день\n" +
                "0. Выход\n");
    }
}
