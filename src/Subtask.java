import java.util.ArrayList;

public class Subtask extends Task {
    int idEpic;

    Subtask(String name, String description, int idEpic) {
        super(name, description);
        this.idEpic = idEpic;
    }
}
