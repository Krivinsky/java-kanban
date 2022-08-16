package managers;

import tasks.Epic;
import tasks.Status;
import tasks.Subtask;
import tasks.Task;


import java.util.ArrayList;


public interface TaskManager {
    ArrayList<Task> getTaskList();
    ArrayList<Subtask> getSubtasksList();
    ArrayList<Epic> getEpicList();

    ArrayList<Task> getPrioritizedTasks();

    void cleanTaskList();
    void cleanSubtaskList();
    void cleanEpicList();

    Task getTaskFromId(int id);
    Task getSubtaskFromId(int id);
    Task getEpicFromId(int id);

    Task creationTask(Task task);
    Subtask creationSubtask(Subtask subtask, int idEpic);
    Epic creationEpic(Epic epic);

    void updateTask(Task task, Status status);
    void updateSubtask(Subtask subtask, Status status, int idEpic);
    void updateEpic(Epic epic);

    void deleteTaskOfId(int id);
    void deleteSubtaskOfId(int id);
    void deleteEpicOfId(int id);

    ArrayList<Subtask> getEpicSubtasksList(Epic epic);
}
