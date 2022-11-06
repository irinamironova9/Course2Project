import java.time.LocalDateTime;
import java.util.Objects;

public class Task {
    enum Type {
        PERSONAL("личная"),
        WORK("рабочая");
        private final String name;

        Type(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    enum Regularity {
        ONE_TIME("однократная"),
        DAILY("ежедневная"),
        WEEKLY("еженедельная"),
        MONTHLY("ежемесячная"),
        YEARLY("ежегодная");
        private final String name;

        Regularity(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
    private final String title;
    private final String description;
    private final Type type;
    private final Regularity regularity;
    private LocalDateTime dateTime;
    private final int id;
    private static int counter = 0;

    protected Task(String title,
                String description,
                Type type,
                Regularity regularity,
                LocalDateTime dateTime) {

        if (title == null || title.isBlank() ||
                description == null || description.isBlank() ||
                type == null || regularity == null ||
                dateTime == null ||
                dateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Заполните все поля!");
        }

        this.title = title;
        this.description = description;
        this.type = type;
        this.regularity = regularity;
        this.dateTime = dateTime;
        counter++;
        this.id = counter;
    }

    @Override
    public String toString() {
        return String.format("Задача: %s\n" +
                "Описание: %s\n" +
                "Тип: %s\n" +
                "Повторяемость: %s\n" +
                "Дата и время: %s\n" +
                "ID: %s\n",
                title,
                description,
                type,
                regularity,
                dateTime,
                id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(title, task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Type getType() {
        return type;
    }

    public Regularity getRegularity() {
        return regularity;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getId() {
        return id;
    }

    public static int getCounter() {
        return counter;
    }

    public void setDateTime(LocalDateTime dateTime) {
        if (dateTime == null || dateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Введите корректную дату!");
        } else {
            this.dateTime = dateTime;
        }
    }
}
