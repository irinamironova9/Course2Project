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
        return updateNextDateTime();
    }

    private LocalDateTime updateNextDateTime() {
        while (nextDateTime.isBefore(LocalDateTime.now()) ||
                nextDateTime.equals(LocalDateTime.now())) {

            if (nextDateTime.getDayOfMonth() ==
                    nextDateTime.plusMonths(1).getDayOfMonth()) {
                nextDateTime = nextDateTime.plusMonths(1);
            } else {
                nextDateTime = nextDateTime.plusMonths(2);
            }
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
                date.getDayOfMonth() == getDateTime().getDayOfMonth();
    }

    @Override
    public String toString() {
        return "Ежемесячная " + super.toString() +
                "\nСледующая дата выполнения: " +
                getNextDateTime();
    }
}
