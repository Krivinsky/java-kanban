package tasks;

public enum Type {
    TASK ("TASK"), EPIC("EPIC"), SUBTASK("SUBTASK");

    final String name;

    Type(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


}
