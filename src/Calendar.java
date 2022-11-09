import tasks.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Calendar {
    private final Map<Integer, Task> tasks = new HashMap<>();

    public void manageTasks() {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            addTask(scanner);
                            break;
                        case 2:
                            removeTask(scanner);
                            break;
                        case 3:
                            displayTasksForDay(scanner);
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

    private static void printMenu() {
        System.out.printf("1. Добавить задачу\n" +
                "2. Удалить задачу\n" +
                "3. Получить задачу на указанный день\n" +
                "0. Выход\n");
    }

    private void addTask(Scanner scanner) {
        String taskName = inputTaskName(scanner);
        while (taskName == null || taskName.isBlank()) {
            System.out.println("Название не может быть пустым! " +
                    "Попробуйте снова.");
            taskName = inputTaskName(scanner);
        }
        System.out.println("Название: " + taskName);


        String taskDescription = inputTaskDescription(scanner);
        while (taskDescription == null || taskDescription.isBlank()) {
            System.out.println("Описание не может быть пустым! " +
                    "Попробуйте снова.");
            taskDescription = inputTaskDescription(scanner);
        }
        System.out.println("Описание: " + taskDescription);


        Task.Type taskType = inputTaskType(scanner);
        while (taskType == null) {
            System.out.println("Некорректно введён тип задачи! " +
                    "Попробуйте снова.");
            taskType = inputTaskType(scanner);
        }


        LocalDate date = inputDate(scanner);
        while (date == null || date.isBefore(LocalDate.now())) {
            System.out.println("Некорректно введена дата или " +
                    "указанный день уже прошёл! " +
                    "Попробуйте снова.");
            date = inputDate(scanner);
        }
        System.out.println("Дата: " + date);


        LocalTime time = inputTime(scanner);
        while (time == null) {
            System.out.println("Некорректно введено время! " +
                    "Попробуйте снова.");
            time = inputTime(scanner);
        }
        System.out.println("Время: " + time);


        LocalDateTime taskDateTime = LocalDateTime.of(date, time);


        int regularity = inputRegularity(scanner);
        while (regularity < 1 || regularity > 5) {
            System.out.println("Некорректно указана повторяемость задачи! " +
                    "Попробуйте снова.");
            regularity = inputRegularity(scanner);
        }


        switch (regularity) {
            case 1:
                Task task1 = new OneTimeTask(taskName,
                        taskDescription,
                        taskType,
                        taskDateTime);
                if (tasks.containsValue(task1)) {
                    System.out.println("Эта задача уже добавлена.");
                } else {
                    this.tasks.put(task1.getId(), task1);
                    System.out.println("Задача успешно добавлена:");
                    System.out.println(task1);
                }
                return;
            case 2:
                Task task2 = new DailyTask(taskName,
                        taskDescription,
                        taskType,
                        taskDateTime);
                if (tasks.containsValue(task2)) {
                    System.out.println("Эта задача уже добавлена.");
                } else {
                    this.tasks.put(task2.getId(), task2);
                    System.out.println("Задача успешно добавлена:");
                    System.out.println(task2);
                }
                return;
            case 3:
                Task task3 = new WeeklyTask(taskName,
                        taskDescription,
                        taskType,
                        taskDateTime);
                if (tasks.containsValue(task3)) {
                    System.out.println("Эта задача уже добавлена.");
                } else {
                    this.tasks.put(task3.getId(), task3);
                    System.out.println("Задача успешно добавлена:");
                    System.out.println(task3);
                }
                return;
            case 4:
                Task task4 = new MonthlyTask(taskName,
                        taskDescription,
                        taskType,
                        taskDateTime);
                if (tasks.containsValue(task4)) {
                    System.out.println("Эта задача уже добавлена.");
                } else {
                    this.tasks.put(task4.getId(), task4);
                    System.out.println("Задача успешно добавлена:");
                    System.out.println(task4);
                }
                return;
            case 5:
                Task task5 = new YearlyTask(taskName,
                        taskDescription,
                        taskType,
                        taskDateTime);
                if (tasks.containsValue(task5)) {
                    System.out.println("Эта задача уже добавлена.");
                } else {
                    this.tasks.put(task5.getId(), task5);
                    System.out.println("Задача успешно добавлена:");
                    System.out.println(task5);
                }
                break;
        }
    }

    private static String inputTaskName(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String taskName = scanner.useDelimiter("\n").next();
        return taskName;
    }

    private static String inputTaskDescription(Scanner scanner) {
        System.out.print("Введите описание задачи: ");
        String taskDescription = scanner.useDelimiter("\n").next();
        return taskDescription;
    }

    private static Task.Type inputTaskType(Scanner scanner) {
        System.out.print("Выберите тип задачи. Нажмите 1 для личной " +
                "или 2 для рабочей задачи: ");
        if (scanner.hasNextInt()) {
            int taskTypeInt = scanner.nextInt();
            switch (taskTypeInt) {
                case 1:
                    System.out.println("Тип задачи: личная");
                    return Task.Type.PERSONAL;
                case 2:
                    System.out.println("Тип задачи: рабочая");
                    return Task.Type.WORK;
                default:
                    return null;
            }
        } else {
            scanner.next();
            return null;
        }
    }

    private static LocalDate inputDate(Scanner scanner) {
        System.out.print("Введите дату задачи в формате гггг-мм-дд: ");
        LocalDate date;
        try {
            date = LocalDate.parse(scanner.next());
            return date;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private static LocalTime inputTime(Scanner scanner) {
        System.out.print("Введите время задачи в формате чч:мм : ");
        LocalTime time;
        try {
            time = LocalTime.parse(scanner.next());
            return time;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private static int inputRegularity(Scanner scanner) {
        System.out.printf("Выберите повторяемость задачи:\n" +
                "1 - однократная\n" +
                "2 - ежедневная\n" +
                "3 - еженедельная\n" +
                "4 - ежемесячная\n" +
                "5 - ежегодная\n");
        if (scanner.hasNextInt()) {
            int i = scanner.nextInt();
            return i;
        } else {
            scanner.next();
            return 0;
        }
    }

    private void removeTask(Scanner scanner) {
        System.out.print("Введите номер id задачи, которую хотите удалить: ");
        if (scanner.hasNextInt()) {
            int input = scanner.nextInt();
            for (Integer key : tasks.keySet()) {
                if (key == input) {
                    tasks.remove(input);
                    System.out.println("Задача успешно удалена.");
                    return;
                }
            }
            System.out.println("Задача с данным id не найдена.");
        } else {
            scanner.next();
            System.out.println("Некорректно введён id!");
        }
    }

    private void displayTasksForDay(Scanner scanner) {
        LocalDate day = inputDate(scanner);
        Set<Task> tasksForDay = new TreeSet<>(new TaskTimeComparator());
        for (Task task : tasks.values()) {
            if (task.appearsIn(day)) {
                tasksForDay.add(task);
            }
        }
        if (tasksForDay.isEmpty()) {
            System.out.printf("На дату %s нет задач.\n", day);
        } else {
            System.out.printf("На дату %s запланировано:\n", day);
            for (Task task : tasksForDay) {
                System.out.println(task);
            }
        }
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "tasks=" + tasks +
                '}';
    }
}
