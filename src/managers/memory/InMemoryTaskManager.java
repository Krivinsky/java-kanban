package managers.memory;

import managers.HistoryManager;
import managers.Managers;
import managers.TaskManager;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;
import tasks.Status;

import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {

    protected static int generateId = 0;

    protected HashMap<Integer, Task> taskMap = new HashMap<>();  //хранить задачи
    protected HashMap<Integer, Subtask> subtaskMap = new HashMap<>();    //хранить подзадачи
    protected HashMap<Integer, Epic> epicMap = new HashMap<>();  // хранить эпики

    public HistoryManager inMemoryHistoryManager = Managers.getDefaultHistory();

    public static int generateId() {                                                                                    //добавил статик
        return ++generateId;
    }

    public ArrayList<Task> getTaskList(){  //Получение списка задач
        return new ArrayList<>(taskMap.values());
    }
    public ArrayList<Subtask> getSubtasksList(){  //Получение списка подзадач
        return new ArrayList<>(subtaskMap.values());
    }
    public ArrayList<Epic> getEpicList(){  //Получение списка эпиков
        return new ArrayList<>(epicMap.values());
    }

    public void cleanTaskList() {    //Удаление всех задач
        for (Task task: taskMap.values()) {
            this.inMemoryHistoryManager.remove(task.getId());
        }
        taskMap.clear();
    }
    public void cleanSubtaskList(){    //Удаление всех подзадач
        for (Subtask subtask:subtaskMap.values()){
            this.inMemoryHistoryManager.remove(subtask.getId());
        }
        subtaskMap.clear();
    }
    public void cleanEpicList(){    //Удаление всех эпиков
        cleanSubtaskList();
        for (Epic epic: epicMap.values()) {
            this.inMemoryHistoryManager.remove(epic.getId());
        }
        epicMap.clear();
    }

    public Task getTaskFromId(int id) {    //Получение задачи по идентификатор
        if (checkId(id)) {
            inMemoryHistoryManager.addTask(taskMap.get(id));
        }
        return taskMap.get(id);
    }
    public Subtask getSubtaskFromId(int id) {    //Получение подзадачи по идентификатор
        if (checkId(id)) {
            inMemoryHistoryManager.addTask(subtaskMap.get(id));
        }
        return subtaskMap.get(id);
    }
    public Epic getEpicFromId(int id) {    //Получение подзадачи по идентификатор
        if (checkId(id)) {
            inMemoryHistoryManager.addTask(epicMap.get(id));
        }
        return epicMap.get(id);
    }

    public Task creationTask(Task task){   //Создание task
        task.setId(generateId());
        task.setStatus(Status.NEW);
        taskMap.put(task.getId(), task);
        return task;
    }
    public Subtask creationSubtask(Subtask subtask, int epicId){   //Создание subtask.
        subtask.setId(generateId());
        subtask.setStatus(Status.NEW);
        subtaskMap.put(subtask.getId(), subtask);
        epicMap.get(epicId).getSubtasksId().add(subtask.getId());
        return subtask;
    }
    public Epic creationEpic(Epic epic){   //Создание epic.
        epic.setId(generateId());
        epic.setStatus(Status.NEW);
        epicMap.put(epic.getId(), epic);
        return epic;
    }

    public void updateTask(Task task, int id, Status status){  //Обновление данных задачи
        if (task != null) {
            task.setStatus(status);
            taskMap.put(id, task);
        }
    }
    public void updateSubtask(Subtask subtask, int idSubtask, Status status, int idEpic){  //Обновление данных подзадачи
        if (subtask != null) {
            subtask.setStatus(status);
            subtaskMap.put(idSubtask, subtask);
            subtask.setIdEpic(idEpic);
            epicMap.get(idEpic).getSubtasksId().add(idSubtask);
        }
    }
    public void updateEpic(Epic epic, int id){  //Обновление данных эпика
        if (epic != null) {
            int countNew = 0;
            int countDone = 0;
            if (epic.getSubtasksId().isEmpty()) {
                epic.setStatus(Status.NEW);
                epicMap.put(id, epic);
                return;
            }
            for (Integer integer : epic.getSubtasksId()) {
                if (subtaskMap.get(integer).getStatus() == Status.DONE) {
                    countDone++;
                } else if (subtaskMap.get(integer).getStatus() == Status.NEW) {
                    countNew++;
                }
            }
            if (countNew == epic.getSubtasksId().size()) {
                epic.setStatus(Status.NEW);
            } else if (countDone == epic.getSubtasksId().size()) {
                epic.setStatus(Status.DONE);
            } else epic.setStatus(Status.IN_PROGRESS);
            epicMap.put(id, epic);
        }
    }

    public void deleteTaskOfId(int id) {    //Удаление задачи по идентификатору
        if (checkId(id)) {
            taskMap.remove(id);
            inMemoryHistoryManager.remove(id);
        }
    }
    public void deleteSubtaskOfId(int id) {    //Удаление подзадачи по идентификатору
        if (checkId(id)) {
            subtaskMap.remove(id);
            for (Epic value : epicMap.values()) {
                value.getSubtasksId().remove(id);
            }
        }
    }
    public void deleteEpicOfId(int id) {    //Удаление эпика по идентификатору
        if (checkId(id)) {
            for (Integer integer : epicMap.get(id).getSubtasksId()) {
                subtaskMap.remove(integer);
            }
            epicMap.remove(id);
        }
    }
/*
*   Из технического задания спринт 3:
*   3. Дополнительные методы:
*      3.1 Получение списка всех подзадач определённого эпика.
* */
    public ArrayList<Subtask> getEpicSubtasksList(Epic epic){  //Получение списка всех подзадач определённого эпика
        ArrayList<Subtask> subtasksList = new ArrayList<>();
        for (Subtask subtask : subtaskMap.values()){
            if (subtask.getIdEpic() == epic.getId()){
                subtasksList.add(subtask);
            }
        }
        return subtasksList;
    }

    private boolean checkId (int id) {
       return 0 < id && id <= generateId;
    }
}