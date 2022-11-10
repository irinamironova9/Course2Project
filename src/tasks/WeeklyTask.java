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
        return updateNextDateTime();
    }

    private LocalDateTime updateNextDateTime() {
        while (nextDateTime.isBefore(LocalDateTime.now()) ||
                nextDateTime.equals(LocalDateTime.now())) {
            nextDateTime = nextDateTime.plusWeeks(1);
        }
        return nextDateTime;
    }

    @Override
    public void displayNextDateTime() {
        System.out.println("Следующая дата и время выполнения задачи - " +
                getNextDateTime());
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        return (getDateTime().toLocalDate().equals(date) ||
                getDateTime().toLocalDate().isBefore(date)) &&
                date.getDayOfWeek() == getDateTime().getDayOfWeek();
    }

    @Override
    public String toString() {
        return "Еженедельная " + super.toString() +
                "\nСледующая дата выполнения: " +
                getNextDateTime();
    }
}
