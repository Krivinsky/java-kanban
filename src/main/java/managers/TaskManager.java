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

    ArrayList<Task> getAllTypesOfTasks();

    void cleanTaskList();
    void cleanSubtaskList();
    void cleanEpicList();

    void cleanAllTypesOfTasksList();

    Task getTaskFromId(int id);
    Task getSubtaskFromId(int id);
    Task getEpicFromId(int id);

    Task getAllTypesOfTasks(int id);

    Task creationTask(Task task);
    Subtask creationSubtask(Subtask subtask, int idEpic);
    Epic creationEpic(Epic epic);

    Task creationAllTypesOfTasks();

    void updateTask(Task task, int id, Status status);
    void updateSubtask(Subtask subtask, Status status, int idEpic);
    void updateEpic(Epic epic, int id);

    void updateAllTypesOfTasks();

    void deleteTaskOfId(int id);
    void deleteSubtaskOfId(int id);
    void deleteEpicOfId(int id);

    void deleteAllTypesOfTasks();

    ArrayList<Subtask> getEpicSubtasksList(Epic epic);
}
