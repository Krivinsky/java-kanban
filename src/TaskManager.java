import java.util.ArrayList;

public interface TaskManager {
    public ArrayList<Task> getTaskList();
    public ArrayList<Subtask> getSubtasksList();
    public ArrayList<Epic> getEpicList();

    public void cleanTaskList();
    public void cleanSubtaskList();
    public void cleanEpicList();

    public Task getTaskFromId(int id);
    public Task getSubtaskFromId(int id);
    public Task getEpicFromId(int id);

    public Task creationTask(Task task);
    public Subtask creationSubtask(Subtask subtask, int idEpic);
    public Epic creationEpic(Epic epic);

    public void updateTask(Task task, int id, Status status);
    public void updateSubtask(Subtask subtask, int idSubtask, Status status, int idEpic);
    public void updateEpic(Epic epic, int id);

    public void deleteTaskOfId(int id);
    public void deleteSubtaskOfId(int id);
    public void deleteEpicOfId(int id);

    public ArrayList<Subtask> getEpicSubtasksList(Epic epic);
}
