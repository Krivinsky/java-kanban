package tasks;

import java.time.LocalDateTime;

public class Subtask extends Task {
    protected int idEpic;

    public Subtask (int id, String name, String description, int idEpic, TypeTask typeTask, Status status, LocalDateTime startTime, long duration) {
        super(id, name, description, typeTask, status, startTime, duration);
        this.idEpic = idEpic;
    }

    public Subtask(String name, String description, int idEpic, TypeTask typeTask, LocalDateTime startTime, long duration) {
        super(name, description, typeTask, startTime, duration);
        this.idEpic = idEpic;
    }

    public Subtask(String name, String description, int idEpic, TypeTask typeTask) {
        super(name, description, typeTask);
        this.idEpic = idEpic;
    }

    public void setIdEpic(int idEpic) {
        this.idEpic = idEpic;
    }

    public int getIdEpic() {
        return idEpic;
    }

    @Override
    public String toString() {
        return super.toString() + "," + idEpic;
    }
}
