package tasks;

public enum Type {
    TASK ("TASK"), EPIC(""), SUBTASK("");

    final String name;

    Type(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
