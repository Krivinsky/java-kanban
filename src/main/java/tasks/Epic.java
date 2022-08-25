package tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class Epic extends Task {
    protected ArrayList<Integer> subtasksId = new ArrayList<>();

    public Epic(int id, String name, String description, TypeTask typeTask, Status status, LocalDateTime startTime, long duration) {
        super(id, name, description, typeTask, status, startTime, duration);
    }

    public Epic(String name, String description, TypeTask typeTask) {
        super(name, description, typeTask);
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

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
