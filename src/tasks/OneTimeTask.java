package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OneTimeTask extends Task {
//    private final String regularityType = "однократная";

    public OneTimeTask(String title,
                       String description,
                       Type type,
                       LocalDateTime dateTime) {
        super(title, description, type, dateTime);
    }

//    public String getRegularityType() {
//        return regularityType;
//    }

    @Override
    public void displayNextDateTime() {
//        if (getDateTime().isAfter(LocalDateTime.now()) ||
//                getDateTime().equals(LocalDateTime.now())) {
//            System.out.println("Дата и время выполнения задачи - " +
//                    getDateTime());
//        } else {
//            System.out.println("Дата и время выполнения задачи уже прошли! " +
//                    "Дата и время были " + getDateTime());
//        }
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        return getDateTime().toLocalDate().equals(date);
    }

    @Override
    public String toString() {
        return "Однократная " + super.toString();
    }
}
