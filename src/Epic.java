import java.util.ArrayList;
import java.util.HashMap;

public class Epic extends Task {
    ArrayList<Subtask> amountOfSubtask = new ArrayList<>();
    ArrayList<Integer> subtasksid = new ArrayList<>();

    Epic(String name, String description) {
        super(name, description);
    }
}
