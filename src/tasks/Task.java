package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task {
    public enum Type {
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
    private final String title;
    private final String description;
    private final Type type;
    private final LocalDateTime dateTime;
    private final int id;
    private static int counter = 0;

    protected Task(String title,
                   String description,
                   Type type,
                   LocalDateTime dateTime) {

        if (title == null || title.isBlank() ||
                description == null || description.isBlank() ||
                type == null || dateTime == null ||
                dateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Заполните все поля!");
        }

        this.title = title;
        this.description = description;
        this.type = type;
        this.dateTime = dateTime;
        counter++;
        this.id = counter;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getId() {
        return id;
    }

    public static int getCounter() {
        return counter;
    }

    public abstract void displayNextDateTime();

    public abstract boolean appearsIn(LocalDate date);

    @Override
    public String toString() {
        return String.format("задача: %s\n" +
                        "Описание: %s\n" +
                        "Тип: %s\n" +
                        "Дата и время: %s\n" +
                        "ID: %s",
                title,
                description,
                type,
                dateTime,
                id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(title, task.title) &&
                Objects.equals(description, task.description) &&
                type == task.type &&
                Objects.equals(dateTime, task.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, type, dateTime);
    }
}