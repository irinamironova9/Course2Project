package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task {
    private final String regularityType = "еженедельная";
    private LocalDateTime nextDateTime;

    public WeeklyTask(String title,
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
            nextDateTime = nextDateTime.plusWeeks(1);
        }
        return nextDateTime;
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        return updateNextDateTime().toLocalDate().equals(date);
    }
}
