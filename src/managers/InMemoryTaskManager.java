package managers;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;
import tasks.Status;

import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {

    private int generateId = 0;

    HashMap<Integer, Task> taskMap = new HashMap<>();  //хранить задачи
    HashMap<Integer, Subtask> subtaskMap = new HashMap<>();    //хранить подзадачи
    HashMap<Integer, Epic> epicMap = new HashMap<>();  // хранить эпики


    HistoryManager inMemoryHistoryManager = Managers.getDefaultHistory();

    public  int generateId(){
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

    public void cleanTaskList(){    //Удаление всех задач
        taskMap.clear();
    }
    public void cleanSubtaskList(){    //Удаление всех подзадач
        subtaskMap.clear();
    }
    public void cleanEpicList(){    //Удаление всех эпиков
        epicMap.clear();
    }

    public Task getTaskFromId(int id) {    //Получение задачи по идентификатор
        inMemoryHistoryManager.addTask(taskMap.get(id));
        return taskMap.get(id);
    }
    public Task getSubtaskFromId(int id) {    //Получение подзадачи по идентификатор
        inMemoryHistoryManager.addTask(subtaskMap.get(id));
        return subtaskMap.get(id);
    }
    public Task getEpicFromId(int id) {    //Получение подзадачи по идентификатор
        inMemoryHistoryManager.addTask(epicMap.get(id));
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

    public void updateTask(Task task, int id, Status status){  //Обновление даных задачи
        task.setStatus(status);
        taskMap.put(id, task);
    }
    public void updateSubtask(Subtask subtask, int idSubtask, Status status, int idEpic){  //Обновление даных подзадачи
        subtask.setStatus(status);
        subtaskMap.put(idSubtask, subtask);
        subtask.setIdEpic(idEpic);
        epicMap.get(idEpic).getSubtasksId().add(idSubtask);

    }
    public void updateEpic(Epic epic, int id){  //Обновление даных эпика
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
        }else if (countDone == epic.getSubtasksId().size()) {
            epic.setStatus(Status.DONE);
        } else epic.setStatus(Status.IN_PROGRESS);
        epicMap.put(id, epic);
    }

    public void deleteTaskOfId(int id){    //Удаление задачи по идентификатору
        taskMap.remove(id);
        inMemoryHistoryManager.remove(id);
    }
    public void deleteSubtaskOfId(int id){    //Удаление подзадачи по идентификатору
        subtaskMap.remove(id);
        for (Epic value : epicMap.values()) {
            value.getSubtasksId().remove(id);
        }
    }
    public void deleteEpicOfId(int id){    //Удаление эпика по идентификатору
        for (Integer integer : epicMap.get(id).getSubtasksId()) {
            subtaskMap.remove(integer);
        }
        epicMap.remove(id);
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
}