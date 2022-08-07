package tasks;

import java.util.ArrayList;

public class Epic extends Task {

    protected ArrayList<Integer> subtasksId = new ArrayList<>();

    public Epic(String name, String description, Type type) {
        super(name, description, type);
    }

    public ArrayList<Integer> getSubtasksId() {
        return subtasksId;
    }
}
