package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

public class DailyTask extends Task {
    private final String regularityType = "ежедневная";
    private LocalDateTime nextDateTime;

    public DailyTask(String title,
                       String description,
                       Type type,
                       LocalDateTime dateTime) {
        super(title, description, type, dateTime);
        nextDateTime = dateTime;
    }

    public String getRegularityType() {
        return regularityType;
    }

    public LocalDateTime getNextDateTime() {
        return nextDateTime;
    }

    @Override
    public void displayNextDateTime() {
        updateNextDateTime();
        System.out.println("Следующая дата и время выполнения задачи - " +
                    getNextDateTime());
    }

    private LocalDateTime updateNextDateTime() {
        while (nextDateTime.isBefore(LocalDateTime.now()) ||
                nextDateTime.equals(LocalDateTime.now())) {
            nextDateTime = nextDateTime.plusDays(1);
        }
        return nextDateTime;
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        nextDateTime = updateNextDateTime();

        return nextDateTime.toLocalDate().equals(date) ||
                nextDateTime.toLocalDate().isAfter(LocalDate.now());
    }

    @Override
    public String toString() {
        return "Ежедневная " + super.toString() +
                "Следующая дата выполнения: " +
                updateNextDateTime();
    }
}
