import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Calendar {
    private final LinkedHashMap<Integer, Task> tasks = new LinkedHashMap<>();

    public Calendar() {
    }

    public void addTask(String title,
                        String description,
                        Task.Type type,
                        Task.Regularity regularity,
                        LocalDateTime dateTime) {

        Task task = new Task(title, description, type, regularity, dateTime);
        tasks.put(task.getId(), task);
    }

    public void removeTask(String title) {
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            if (entry.getValue().getTitle().equals(title)) {
                System.out.println("Задача удалена.");
                tasks.remove(entry.getKey());
                return;
            }
        }
    }

    public void printTasksForTheDay(LocalDate date) {
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            LocalDate ld = LocalDate.of(entry.getValue().getDateTime().getYear(),
                    entry.getValue().getDateTime().getMonth(),
                    entry.getValue().getDateTime().getDayOfMonth());
            if (ld.equals(date)) {
                System.out.println(entry.getValue());
            }
        }
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
