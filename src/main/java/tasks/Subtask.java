package tasks;

public class Subtask extends Task {
    protected int idEpic;

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