package tasks;

import java.time.Duration;
import java.time.LocalDateTime;

public class Subtask extends Task {
    protected int idEpic;

    public Subtask (int id, String name, String description, int idEpic, Type type, Status status, LocalDateTime startTime, long duration) {
        super(id, name, description, type, status, startTime, duration);
        this.idEpic = idEpic;
    }

    public Subtask(String name, String description, int idEpic, Type type, LocalDateTime startTime, long duration) {
        super(name, description, type, startTime, duration);
        this.idEpic = idEpic;
    }

    public Subtask(String name, String description, int idEpic, Type type) {
        super(name, description, type);
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
