package Tasks;

import java.util.ArrayList;

public class Epic extends Task {
    protected ArrayList<Integer> subtasksid = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
    }

    public ArrayList<Integer> getSubtasksid() {
        return subtasksid;
    }

    public void setSubtasksid(ArrayList<Integer> subtasksid) {
        this.subtasksid = subtasksid;
    }
}
