package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthlyTask extends Task {
    private final String regularityType = "ежемесячная";
    private LocalDateTime nextDateTime;

    public MonthlyTask(String title,
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
            nextDateTime = nextDateTime.plusMonths(1);
        }
        return nextDateTime;
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        return updateNextDateTime().toLocalDate().equals(date);
    }

    @Override
    public String toString() {
        return "Ежемесячная " + super.toString() +
                "Следующая дата выполнения: " +
                updateNextDateTime();
    }
}
