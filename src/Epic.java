import java.util.ArrayList;

public class Epic extends Task {
    ArrayList<Subtask> amountOfSubtask = new ArrayList<>();

    Epic(String name, String description) {
        super(name, description);
    }
}
