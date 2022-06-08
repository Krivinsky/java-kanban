import java.util.ArrayList;

public class Subtask extends Task {
    String nameOfEpic;
    int idEpic;

    Subtask(String name, String description, String nameOfEpic) {
        super(name, description);
        this.nameOfEpic = nameOfEpic;
    }
}
