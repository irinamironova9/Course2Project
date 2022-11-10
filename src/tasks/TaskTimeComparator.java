package tasks;

import java.util.Comparator;

public class TaskTimeComparator implements Comparator<Task> {
    @Override
    public int compare(Task task1, Task task2) {
        return task1.getDateTime().toLocalTime().compareTo(
                task2.getDateTime().toLocalTime());
    }
}