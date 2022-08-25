package tasks;

public enum TypeTask {
    TASK ("TASK"), EPIC("EPIC"), SUBTASK("SUBTASK");

    final String name;

    TypeTask(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


}
