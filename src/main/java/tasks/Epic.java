package tasks;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class Epic extends Task {
    Duration duration; //todo  time
    LocalDateTime startTime; //todo  time
    protected ArrayList<Integer> subtasksId = new ArrayList<>();

    public Epic(int id, String name, String description, Type type, Status status, LocalDateTime startTime, long duration) {
        super(id, name, description, type, status, startTime, duration);
    }

    public Epic(String name, String description, Type type, LocalDateTime startTime, long duration) {
        super(name, description, type, startTime, duration);
    }

    public ArrayList<Integer> getSubtasksId() {
        return subtasksId;
    }

    public void addSubtasksId(int subtaskId) {
        subtasksId.add(subtaskId);
    }

    public void removeIdFromSubtasksIdList(int subtaskId) {
        Iterator<Integer> iterator = subtasksId.iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if ((int) element == subtaskId) {
                iterator.remove();
            }
        }
    }
}
