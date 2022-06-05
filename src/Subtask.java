import java.util.ArrayList;

public class Subtask extends Task {
    String nameOfEpic;


    Subtask(String name, String description, String nameOfEpic) {
        super(name, description);
        this.nameOfEpic = nameOfEpic;
    }
}
